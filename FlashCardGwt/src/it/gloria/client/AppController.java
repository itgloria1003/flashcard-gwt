package it.gloria.client;

import it.gloria.client.event.AddFlashCardEvent;
import it.gloria.client.event.AddFlashCardEventHandler;
import it.gloria.client.event.EditFlashCardCancelledEvent;
import it.gloria.client.event.EditFlashCardCancelledEventHandler;
import it.gloria.client.event.EditFlashCardEvent;
import it.gloria.client.event.EditFlashCardEventHandler;
import it.gloria.client.event.FlashCardUpdatedEvent;
import it.gloria.client.event.FlashCardUpdatedEventHandler;
import it.gloria.client.presenter.EditFlashCardPresenter;
import it.gloria.client.presenter.FlashCardsPresenter;
import it.gloria.client.presenter.Presenter;
import it.gloria.client.view.EditFlashCardView;
import it.gloria.client.view.FlashCardsView;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
  private static final String ACTION_EDIT = "edit";
private static final String ACTION_ADD = "add";
private static final String ACTION_LIST = "list";
private final HandlerManager eventBus;
  private final FlashCardServiceAsync rpcService; 
  private HasWidgets container;
  
  public AppController(FlashCardServiceAsync rpcService, HandlerManager eventBus) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    bind();
  }
  
  
  // set the history and add the event 
  private void bind() {
	  
	  
    History.addValueChangeHandler(this);

    eventBus.addHandler(AddFlashCardEvent.TYPE,
        new AddFlashCardEventHandler() {
          public void onAddFlashCard(AddFlashCardEvent event) {
            doAddNewFlashCard();
          }
        });  

    eventBus.addHandler(EditFlashCardEvent.TYPE,
        new EditFlashCardEventHandler() {
          public void onEditFlashCard(EditFlashCardEvent event) {
            doEditFlashCard(event.getIdKey());
          }
        });  

    eventBus.addHandler(EditFlashCardCancelledEvent.TYPE,
        new EditFlashCardCancelledEventHandler() {
          public void onEditFlashCardCancelled(EditFlashCardCancelledEvent event) {
            doEditFlashCardCancelled();
          }
        });  

    eventBus.addHandler(FlashCardUpdatedEvent.TYPE,
        new FlashCardUpdatedEventHandler() {
          public void onFlashCardUpdated(FlashCardUpdatedEvent event) {
            doFlashCardUpdated();
          }
        });  
  }
  
  private void doAddNewFlashCard() {
    History.newItem(ACTION_ADD);
  }
  
  private void doEditFlashCard(Long id) {
    History.newItem(ACTION_EDIT, false);
    Presenter presenter = new EditFlashCardPresenter(rpcService, eventBus, new EditFlashCardView(), id);
    presenter.go(container);
  }
  
  private void doEditFlashCardCancelled() {
    History.newItem(ACTION_LIST);
  }
  
  private void doFlashCardUpdated() {
    History.newItem(ACTION_LIST);
  }
  
  public void go(final HasWidgets container) {
    this.container = container;
    
    if ("".equals(History.getToken())) {
      History.newItem(ACTION_LIST);
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {
    String token = event.getValue();
    
    if (token != null) {
      Presenter presenter = null;

      if (token.equals(ACTION_LIST)) {
        presenter = new FlashCardsPresenter(rpcService, eventBus, new FlashCardsView());
      }
      else if (token.equals(ACTION_ADD)) {
        presenter = new EditFlashCardPresenter(rpcService, eventBus, new EditFlashCardView(), null);
      }
      else if (token.equals(ACTION_EDIT)) {
        presenter = new EditFlashCardPresenter(rpcService, eventBus, new EditFlashCardView(), null);
      }
      
      if (presenter != null) {
        presenter.go(container);
      }
    }
  } 
}
