package it.gloria.client.presenter;

import it.gloria.client.FlashCardServiceAsync;
import it.gloria.client.event.AddFlashCardEvent;
import it.gloria.client.event.EditFlashCardEvent;
import it.gloria.shared.FlashCard;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class FlashCardsPresenter implements Presenter {  

  private List<FlashCard> flashCardList;

  
  // the widget in the screen
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
  
  
  // bind 
  public void bind() {
	  
    display.getAddButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        eventBus.fireEvent(new AddFlashCardEvent());
      }
    });

    display.getDeleteButton().addClickHandler(new ClickHandler() {   
      public void onClick(ClickEvent event) {
        deleteSelectedFlashCards();
      }
    });
    
    display.getList().addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        int selectedItem = display.getClickedItem(event);
        
        if (selectedItem >= 0) {
          Long id = flashCardList.get(selectedItem).getIdKey();
          eventBus.fireEvent(new EditFlashCardEvent(id));
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

  public void setflashCardList(List<FlashCard> flashCardList) {
    this.flashCardList = flashCardList;
  }
  
  public FlashCard getFlashCardDetail(int index) {
    return flashCardList.get(index);
  }
  
  private void fetchflashCardList() {
    rpcService.listFlashCards(new AsyncCallback<ArrayList<FlashCard>>() {
		
		@Override
		public void onSuccess(ArrayList<FlashCard> result) {
			// TODO Auto-generated method stub
			flashCardList = result;
			
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}
	});
    
  }

  private void deleteSelectedFlashCards() {
    List<Integer> selectedRows = display.getSelectedRows();
    ArrayList<Long> ids = new ArrayList<Long>();
    
    for (int i = 0; i < selectedRows.size(); ++i) {
      ids.add(flashCardList.get(selectedRows.get(i)).getIdKey());
    }
    
    rpcService.deleteFlashCards(ids, new AsyncCallback<ArrayList<FlashCard>>() {
      public void onSuccess(ArrayList<FlashCard> result) {
        flashCardList = result;
        sortflashCardList();
        List<String> data = new ArrayList<String>();

        for (int i = 0; i < result.size(); ++i) {
          data.add(flashCardList.get(i).getEngCaption());
        }
        
        display.setData(data);
        
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error deleting selected FlashCards");
      }
    });
  }
}
