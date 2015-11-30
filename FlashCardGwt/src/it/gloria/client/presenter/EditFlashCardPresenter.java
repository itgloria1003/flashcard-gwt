package it.gloria.client.presenter;

import it.gloria.client.FlashCardServiceAsync;
import it.gloria.client.event.EditFlashCardCancelledEvent;
import it.gloria.client.event.FlashCardUpdatedEvent;
import it.gloria.shared.FlashCard;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class EditFlashCardPresenter implements Presenter{  
  
 public interface Display {
    HasClickHandlers getSaveButton();
    HasClickHandlers getCancelButton();
    HasValue<String> getChinCaption();
    HasValue<String> getEngCaption();
    Widget asWidget();
  }
  
  private FlashCard flashCard;
  private final FlashCardServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display displayComponent;
  
  
  
  
  public EditFlashCardPresenter(FlashCardServiceAsync rpcService, HandlerManager eventBus, Display display, Long idKey) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.displayComponent = display;
    bind();
    
    rpcService.getFlashCard(idKey, new AsyncCallback<FlashCard>() {
      public void onSuccess(FlashCard result) {
        flashCard = result;
        EditFlashCardPresenter.this.displayComponent.getChinCaption().setValue(flashCard.getChinCaption());
        EditFlashCardPresenter.this.displayComponent.getEngCaption().setValue(flashCard.getEngCaption());
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error retrieving FlashCard");
      }
    });
    
  }
  
  public void bind() {
    this.displayComponent.getSaveButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        doSave();
      }
    });

    this.displayComponent.getCancelButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new EditFlashCardCancelledEvent());
      }
    });
  }

  public void go(final HasWidgets container) {
    container.clear();
    container.add(displayComponent.asWidget());
  }

  private void doSave() {
	  
	  // to be replace by 
	flashCard.setEngCaption(displayComponent.getEngCaption().getValue());
	flashCard.setChinCaption(displayComponent.getChinCaption().getValue());
    
    rpcService.updateFlashCard(flashCard, new AsyncCallback<FlashCard>() {
        public void onSuccess(FlashCard result) {
          eventBus.fireEvent(new FlashCardUpdatedEvent(result));
        }
        public void onFailure(Throwable caught) {
          Window.alert("Error updating FlashCard");
        }
    });
  }
  
}
