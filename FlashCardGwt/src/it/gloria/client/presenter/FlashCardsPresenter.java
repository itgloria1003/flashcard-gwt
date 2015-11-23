package it.gloria.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

import it.gloria.client.FlashCardServiceAsync;
import it.gloria.shared.FlashCard;

import java.util.ArrayList;
import java.util.List;

public class FlashCardsPresenter implements Presenter {  

  private List<FlashCard> flashCardList;

  public interface Display {
    HasClickHandlers getAddButton();
    HasClickHandlers getDeleteButton();
    HasClickHandlers getList();
    void setData(List<String> data);
    int getClickedItem(ClickEvent event);
    List<Integer> getSelectedRows();
    Widget asWidget();
  }
  
  private final FlashCardServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final Display display;
  
  public FlashCardsPresenter(FlashCardServiceAsync rpcService, HandlerManager eventBus, Display view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.display = view;
  }
  
  public void bind() {
    display.getAddButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new AddContactEvent());
      }
    });

    display.getDeleteButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        deleteSelectedContacts();
      }
    });
    
    display.getList().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        int selectedRow = display.getClickedRow(event);
        
        if (selectedRow >= 0) {
          String id = flashCardList.get(selectedRow).getId();
          eventBus.fireEvent(new EditContactEvent(id));
        }
      }
    });
  }
  
  public void go(final HasWidgets container) {
    bind();
    container.clear();
    container.add(display.asWidget());
    fetchflashCardList();
  }

  public void sortflashCardList() {
    
    // Yes, we could use a more optimized method of sorting, but the 
    //  point is to create a test case that helps illustrate the higher
    //  level concepts used when creating MVP-based applications. 
    //
    for (int i = 0; i < flashCardList.size(); ++i) {
      for (int j = 0; j < flashCardList.size() - 1; ++j) {
        if (flashCardList.get(j).getDisplayName().compareToIgnoreCase(flashCardList.get(j + 1).getDisplayName()) >= 0) {
          flashCardList tmp = flashCardList.get(j);
          flashCardList.set(j, flashCardList.get(j + 1));
          flashCardList.set(j + 1, tmp);
        }
      }
    }
  }

  public void setflashCardList(List<flashCardList> flashCardList) {
    this.flashCardList = flashCardList;
  }
  
  public flashCardList getContactDetail(int index) {
    return flashCardList.get(index);
  }
  
  private void fetchflashCardList() {
    rpcService;
  }

  private void deleteSelectedContacts() {
    List<Integer> selectedRows = display.getSelectedRows();
    ArrayList<String> ids = new ArrayList<String>();
    
    for (int i = 0; i < selectedRows.size(); ++i) {
      ids.add(flashCardList.get(selectedRows.get(i)).getId());
    }
    
    rpcService.deleteContacts(ids, new AsyncCallback<ArrayList<flashCardList>>() {
      public void onSuccess(ArrayList<flashCardList> result) {
        flashCardList = result;
        sortflashCardList();
        List<String> data = new ArrayList<String>();

        for (int i = 0; i < result.size(); ++i) {
          data.add(flashCardList.get(i).getDisplayName());
        }
        
        display.setData(data);
        
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error deleting selected contacts");
      }
    });
  }
}
