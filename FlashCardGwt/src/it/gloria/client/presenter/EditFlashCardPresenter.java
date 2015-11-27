package it.gloria.client.presenter;

import it.gloria.client.FlashCardServiceAsync;
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
    HasValue<String> getFirstName();
    HasValue<String> getLastName();
    HasValue<String> getEmailAddress();
    Widget asWidget();
  }
  
  private FlashCard flashCard;
  private final FlashCardServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public EditFlashCardPresenter(FlashCardServiceAsync rpcService, HandlerManager eventBus, Display display) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.flashCard = new FlashCard();
    this.display = display;
    bind();
  }
  
  public EditFlashCardPresenter(FlashCardServiceAsync rpcService, HandlerManager eventBus, Display display, String id) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = display;
    bind();
    
    rpcService.getFlashCard(id, new AsyncCallback<FlashCard>() {
      public void onSuccess(FlashCard result) {
        flashCard = result;
        EditFlashCardPresenter.this.display.getFirstName().setValue(flashCard.getFirstName());
        EditFlashCardPresenter.this.display.getLastName().setValue(flashCard.getLastName());
        EditFlashCardPresenter.this.display.getEmailAddress().setValue(flashCard.getEmailAddress());
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error retrieving FlashCard");
      }
    });
    
  }
  
  public void bind() {
    this.display.getSaveButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        doSave();
      }
    });

    this.display.getCancelButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new EditFlashCardCancelledEvent());
      }
    });
  }

  public void go(final HasWidgets container) {
    container.clear();
    container.add(display.asWidget());
  }

  private void doSave() {
    flashCard.setFirstName(display.getFirstName().getValue());
    flashCard.setLastName(display.getLastName().getValue());
    flashCard.setEmailAddress(display.getEmailAddress().getValue());
    
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
