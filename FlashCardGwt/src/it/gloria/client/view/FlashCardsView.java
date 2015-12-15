package it.gloria.client.view;

import it.gloria.client.presenter.FlashCardsPresenter;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FlashCardsView extends Composite implements
		FlashCardsPresenter.Display {

	private static FlashCardsViewUiBinder uiBinder = GWT
			.create(FlashCardsViewUiBinder.class);

	interface FlashCardsViewUiBinder extends UiBinder<Widget, FlashCardsView> {
	}

	public FlashCardsView() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button button;

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	@Override
	public HasClickHandlers getAddButton() {
		return button;
	}

	@Override
	public HasClickHandlers getDeleteButton() {
		return button;
	}

	@Override
	public void setData(List<String> data) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Integer> getSelectedRows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void promptMessage(String message) {
		// TODO Auto-generated method stub

	}

}
