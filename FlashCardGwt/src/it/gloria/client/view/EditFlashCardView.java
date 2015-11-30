package it.gloria.client.view;

import it.gloria.client.presenter.EditFlashCardPresenter;
import it.gloria.shared.FlashCard;

import com.github.gwtbootstrap.client.ui.ControlGroup;
import com.github.gwtbootstrap.client.ui.FileUpload;
import com.github.gwtbootstrap.client.ui.Form;
import com.github.gwtbootstrap.client.ui.HelpInline;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class EditFlashCardView extends Composite implements EditFlashCardPresenter.Display , Editor<FlashCard> {
  
	  // create the ui field 
	
		@UiField
		Form createForm;

		@UiField
		@Editor.Ignore
		Label title;

		@UiField
		@Editor.Ignore
		FileUpload uploadFile;

		@UiField
		Button cancelButton;

		@UiField
		TextBox engCaption;
		

		@UiField
		TextBox chinCaption;

		@UiField
		ControlGroup engCaptionControlGroup;
		

		@UiField
		@Editor.Ignore
		HelpInline engCaptionHelp;


		@Override
		public HasClickHandlers getSaveButton() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public HasClickHandlers getCancelButton() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public HasValue<String> getChinCaption() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public HasValue<String> getEngCaption() {
			// TODO Auto-generated method stub
			return null;
		}

	
  
}
