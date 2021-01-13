package com.generali.fipos.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

@SuppressWarnings("serial")
@PreserveOnRefresh
@Route("problem")
public class Dispatcher extends VerticalLayout implements LocaleChangeObserver, HasUrlParameter<String> {

	public Dispatcher() {
		
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		setContent(parameter);
	}

	@Override
	public void localeChange(LocaleChangeEvent event) {
	}

	private void setContent(String parameter) {
		removeAll();
		Component view = Context.instance().getView(parameter);
		view.getElement().removeFromTree();
		add(view);
	}
}
