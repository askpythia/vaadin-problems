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
		add(new Paragraph("When a Tree inside a Grid Detail changes it's vertical size (open, close a Node) the sizing of the Detail is corrupt. When e.g. pressing (Ctrl)-(+) and/or (Ctrl)-(-) to change broswer size the Grid Detail will be correctly sized afterwards. Should be sized correctly direct after inner size change.ionaly this should also work inside a dialog, in the example there are two dialogs, one with only a div and paragraphs inide the details (static, so no resizing can happen later) and one with the tree inside the details where the resize makes problems. The Vaadin Version 14.4.5 additionally had the problem that the tree inside the details was not displayed entirely and when trying there were dozents of js errors (you can try when switching the example to 14.4.5). Up to 14.4.4 and again from 14.4.6 this behavior did not apply."));
	}
}
