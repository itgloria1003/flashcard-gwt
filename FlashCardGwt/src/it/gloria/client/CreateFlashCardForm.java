package it.gloria.client;

import it.gloria.shared.FlashCard;
import it.gloria.shared.FlashCardVerifier;
import it.gloria.shared.ValidationMessage;
import it.gloria.shared.ValidationMessage.MessageType;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.FileUpload;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.Form.SubmitCompleteEvent;
import com.github.gwtbootstrap.client.ui.Form.SubmitCompleteHandler;
import com.github.gwtbootstrap.client.ui.Form.SubmitEvent;
import com.github.gwtbootstrap.client.ui.Form.SubmitHandler;
import com.github.gwtbootstrap.client.ui.HelpInline;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Widget;

public class CreateFlashCardForm extends Composite implements Editor<FlashCard> {

	interface CreateFlashCardFormUiBinder extends
			UiBinder<Widget, CreateFlashCardForm> {
	}

	FlashCardServiceAsync flashCardService = GWT.create(FlashCardService.class);

	interface Driver extends
			SimpleBeanEditorDriver<FlashCard, CreateFlashCardForm> {
	}

	private static CreateFlashCardFormUiBinder uiBinder = GWT
			.create(CreateFlashCardFormUiBinder.class);

	@UiField
	Form createForm;

	@UiField
	@Editor.Ignore
	Label title;

	@UiField
	@Editor.Ignore
	FileUpload uploadFile;

	Driver driver = GWT.create(Driver.class);

	@UiField
	Button resetButton;

	@UiField
	TextBox engCaption;

	@UiField
	ControlGroup engCaptionControlGroup;

	@UiField
	@Editor.Ignore
	HelpInline engCaptionHelp;

	public CreateFlashCardForm() {
		initWidget(uiBinder.createAndBindUi(this));

		driver.initialize(this);
		setFlashCard(new FlashCard());
		
		// Now we use out GWT-RPC service and get an URL
        startNewBlobstoreSession();
		createForm.setMethod(FormPanel.METHOD_POST);
		createForm.setEncoding(FormPanel.ENCODING_MULTIPART);
		createForm.addSubmitHandler(new SubmitHandler() {
			
			@Override
			public void onSubmit(SubmitEvent event) {
				// Update the object being edited with the current state of the Editor.
				FlashCard card = driver.flush();
				ValidationMessage validateEngDesc = FlashCardVerifier
						.validateEngDesc(card.getEngCaption());
				if (validateEngDesc.getType() == MessageType.ERROR) {
					engCaptionHelp.setText(validateEngDesc.getMessage());
					engCaptionControlGroup.setType(ControlGroupType.ERROR);
				}
				;
			}
		});
		createForm.addSubmitCompleteHandler(new SubmitCompleteHandler() {

			@Override
			public void onSubmitComplete(SubmitCompleteEvent event) {
				FlashCard card = driver.flush();
				flashCardService.updateFlashCardDetails(card,
						new AsyncCallback<String>() {

							@Override
							public void onSuccess(String result) {
								createForm.reset();
								startNewBlobstoreSession();
								Window.alert("Result from server:"+ result);

							}

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub

							}
						});
			}
		});

	}

	private void setFlashCard(FlashCard flashCard) {
		// TODO Auto-generated method stub
		driver.edit(flashCard);

	}

	@UiHandler("resetButton")
	public void onCancelClick(ClickEvent e) {
		createForm.reset();
	}



	private void startNewBlobstoreSession() {
		flashCardService.getBlobstoreUploadUrl(new AsyncCallback<String>() {

			@Override
			public void onSuccess(String result) {
				createForm.setAction(result);
				uploadFile.setEnabled(true);

			}

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
	}

}
