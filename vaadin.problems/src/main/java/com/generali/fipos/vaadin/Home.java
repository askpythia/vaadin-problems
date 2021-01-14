package com.generali.fipos.vaadin;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@SuppressWarnings("serial")
@Route("")
public class Home extends VerticalLayout {
	public Home() {
		add(new H1("Vaadin Problem Samples"));
		add(new H2("Vaadin Version: 14.4.6"));
		add(new RouterLink("Resize Problem in Grid Details", Dispatcher.class, "DialogGridDetails"));
		add(new Paragraph("When a Tree inside a Grid Detail changes it's vertical size (open, close a Node) the sizing of the Detail is corrupt. When e.g. pressing (Ctrl)-(+) and/or (Ctrl)-(-) to change broswer size the Grid Detail will be correctly sized afterwards. Should be sized correctly direct after inner size change."));
	}
}
