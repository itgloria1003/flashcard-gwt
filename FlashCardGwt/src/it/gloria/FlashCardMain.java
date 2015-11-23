package it.gloria;

import it.gloria.client.FlashCardService;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class FlashCardMain implements EntryPoint {

  public void onModuleLoad() {
    FlashCardService rpcService = GWT.create(FlashCardService.class);
    HandlerManager eventBus = new HandlerManager(null);
    AppController appViewer = new AppController(rpcService, eventBus);
    appViewer.go(RootPanel.get());
  }
}
