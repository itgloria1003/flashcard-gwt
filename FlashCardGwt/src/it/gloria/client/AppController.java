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
  private final HandlerManager eventBus;
  private final FlashCardServiceAsync rpcService; 
  private HasWidgets container;
  
  public AppController(FlashCardServiceAsync rpcService, HandlerManager eventBus) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    bind();
  }
  
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
    History.newItem("add");
  }
  
  private void doEditFlashCard(String id) {
    History.newItem("edit", false);
    Presenter presenter = new EditFlashCardPresenter(rpcService, eventBus, new EditFlashCardView(), id);
    presenter.go(container);
  }
  
  private void doEditFlashCardCancelled() {
    History.newItem("list");
  }
  
  private void doFlashCardUpdated() {
    History.newItem("list");
  }
  
  public void go(final HasWidgets container) {
    this.container = container;
    
    if ("".equals(History.getToken())) {
      History.newItem("list");
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {
    String token = event.getValue();
    
    if (token != null) {
      Presenter presenter = null;

      if (token.equals("list")) {
        presenter = new FlashCardsPresenter(rpcService, eventBus, new FlashCardsView());
      }
      else if (token.equals("add")) {
        presenter = new EditFlashCardPresenter(rpcService, eventBus, new EditFlashCardView());
      }
      else if (token.equals("edit")) {
        presenter = new EditFlashCardPresenter(rpcService, eventBus, new EditFlashCardView());
      }
      
      if (presenter != null) {
        presenter.go(container);
      }
    }
  } 
}