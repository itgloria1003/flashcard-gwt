package it.gloria.client;



import Person;
import it.gloria.shared.FlashCard;
import it.gloria.shared.FlashCardVerifier;
import it.gloria.shared.ValidationMessage;
import it.gloria.shared.ValidationMessage.MessageType;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.Form.SubmitEvent;
import com.github.gwtbootstrap.client.ui.constants.ControlGroupType;
import com.github.gwtbootstrap.client.ui.HelpInline;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class CreateFlashCardForm extends Composite implements Editor<FlashCard> {

	interface CreateFlashCardFormUiBinder extends
			UiBinder<Widget, CreateFlashCardForm> {
	}

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
	
	Driver driver = GWT.create(Driver.class);

	@UiField
	Modal modalWindow;

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
	}

	private void setFlashCard(FlashCard flashCard) {
		// TODO Auto-generated method stub
		driver.edit(flashCard);
		
	}

	@UiHandler("resetButton")
	public void onCancelClick(ClickEvent e) {
		createForm.reset();
	}

	@UiHandler("createForm")
	public void onSubmit(SubmitEvent event) {
		//Update the object being edited with the current state of the Editor.
		FlashCard card= driver.flush();
		ValidationMessage validateEngDesc = FlashCardVerifier.validateEngDesc(card.getEngCaption());
		if (validateEngDesc.getType() == MessageType.ERROR){
			engCaptionHelp.setText(validateEngDesc.getMessage());
			engCaptionControlGroup.setType(ControlGroupType.ERROR);
		};
		title.setText("submitt@");
		
		
	}

	public void show() {
		modalWindow.show();
	}

}
