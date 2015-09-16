package it.gloria.client;



import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FlashCardGwt implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {

		HorizontalPanel hp = new HorizontalPanel();
        hp.setSpacing(20);
//         
//        
//        GWTCropper cropper = new GWTCropper("Gloria_kinkin.jpg");
////        hp.add(cropper);
//
//        GWTCropperPreview cropperPreview = new GWTCropperPreview(Dimension.WIDTH, 100);
//        cropper.registerPreviewWidget(cropperPreview);
//        hp.add(cropperPreview);
        

        final CreateFlashCardForm newFlashCardForm = new CreateFlashCardForm();

		
        Button createButton = new Button("Create a flash card");
        createButton.setIcon(IconType.PLUS);
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				newFlashCardForm.show();		
				
			}
		});
        hp.add(createButton);
        
        RootPanel.get().add(hp);
        
        
	}
}
