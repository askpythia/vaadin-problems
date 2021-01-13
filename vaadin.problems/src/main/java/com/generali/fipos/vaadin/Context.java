package com.generali.fipos.vaadin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.generali.fipos.vaadin.util.ViewCache;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

@Configuration
public class Context {

	private static Context context;

	public Context() {
		context = this;
	}

	public static Context instance() {
		return context;
	}

	@Bean
	@VaadinSessionScope
	public ViewCache getViewCache() {
		return new ViewCache();
	}

	public Component getView(String name) {
		return getViewCache().getView(name);
	}
}
