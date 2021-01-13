package com.generali.fipos.vaadin.util;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Paragraph;

public class ViewCache {

	private Map<String, Component> cache = new HashMap<>();

	public Component getView(String name) {
		return cache.computeIfAbsent(name, k -> findView(k));
	}

	private Component findView(String name) {
		try {
			Class<?> problemClass = Class.forName("com.generali.fipos.vaadin.problems." + name);
			return (Component) problemClass.getDeclaredConstructor().newInstance();
		} catch (Exception ex) {
			return nameNotFoundComponent(name);
		}
	}

	private Component nameNotFoundComponent(String name) {
		Paragraph paragraph = new Paragraph();
		paragraph.setText("Can't find view names '" + name + "'!");
		return paragraph;
	}
}
