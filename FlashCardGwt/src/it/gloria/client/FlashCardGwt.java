package it.gloria.client;



import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Collapse;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.Section;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class FlashCardGwt implements EntryPoint {

	@UiField
    FlowPanel sections;
	
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Section section = new Section("sectionForm");		
		
		final Collapse cp = new Collapse();
		cp.setDefaultOpen(false);
		CreateFlashCardForm form =  new CreateFlashCardForm();		
		cp.add(form);
		

        Button createButton = new Button("Create a flash card");
        createButton.setIcon(IconType.PLUS);
        createButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				cp.toggle();		
				
			}
		});
        section.add(createButton);        
        section.add(cp);
//         
//        
//        GWTCropper cropper = new GWTCropper("Gloria_kinkin.jpg");
////        hp.add(cropper);
//
//        GWTCropperPreview cropperPreview = new GWTCropperPreview(Dimension.WIDTH, 100);
//        cropper.registerPreviewWidget(cropperPreview);
//        hp.add(cropperPreview);
        
		
        
        
        RootPanel.get().add(section);
        
        
	}
}
