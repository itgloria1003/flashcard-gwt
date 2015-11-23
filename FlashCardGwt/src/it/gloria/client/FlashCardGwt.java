package it.gloria.client;

import it.gloria.shared.FlashCard;

import java.util.ArrayList;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Collapse;
import com.github.gwtbootstrap.client.ui.Column;
import com.github.gwtbootstrap.client.ui.Heading;
import com.github.gwtbootstrap.client.ui.Row;
import com.github.gwtbootstrap.client.ui.Section;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FlashCardGwt implements EntryPoint {

	private static final int COLUMN_PER_ROW = 3;

	FlashCardServiceAsync flashCardService = GWT.create(FlashCardService.class);

	/**
	 * This is the entry point method.
	 */
	public void refresh() {
//		Window.alert("refresh");
		sectionList.clear();
		flashCardService
				.listFlashCards(new AsyncCallback<ArrayList<FlashCard>>() {

					@Override
					public void onSuccess(ArrayList<FlashCard> result) {
//						Window.alert(result.size() +"");
						int colIdx = 0;
						Row row = new Row();
						for (FlashCard card : result) {
							ListFlashCardPanel panel = new ListFlashCardPanel();
//							Window.alert("ID Key from server" + card.getIdKey());
							panel.init(flashCardService, card);
							if (colIdx % COLUMN_PER_ROW == 0) {
								row = new Row();
							}
							Column column = new Column(12 / COLUMN_PER_ROW);
							column.add(panel);
							row.add(column);
							sectionList.add(row);
							colIdx++;

						}

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});

	};

	
	Section sectionForm = new Section("sectionForm");

	public void onModuleLoad() {
		FlowPanel sections = new FlowPanel();

		final Collapse cp = new Collapse();
		cp.setDefaultOpen(false);
		CreateFlashCardForm form = new CreateFlashCardForm();
		form.setFlashCardGwt(this);

		cp.add(form);

		Button createButton = new Button("Create a flash card");
		createButton.setIcon(IconType.PLUS);
		createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cp.toggle();

			}
		});
		sectionForm.add(createButton);
		sectionForm.add(cp);
		

		sectionList.add(new Heading(3,"Flash Card Collection"));
		refresh();

		
		//
		//
		// GWTCropper cropper = new GWTCropper("Gloria_kinkin.jpg");
		// // hp.add(cropper);
		//
		// GWTCropperPreview cropperPreview = new
		// GWTCropperPreview(Dimension.WIDTH, 100);
		// cropper.registerPreviewWidget(cropperPreview);
		// hp.add(cropperPreview);
		sections.add(sectionForm);
		sections.add(sectionList);
		RootPanel.get().add(sections);
	
		

	}
}
