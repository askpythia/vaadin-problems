package com.generali.fipos.vaadin;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@SuppressWarnings("serial")
@CssImport(value = "./styles/gli-styles.css")
@Route("")
public class Home extends VerticalLayout {
	public Home() {
		add(new H1("Vaadin Problem Samples"));

		add(new H2("PROBLEM 1 : Vaadin Version: 14.4.6"));
		add(new RouterLink("Resize Problem in Grid Details", Dispatcher.class, "DialogGridDetails"));
		add(new Paragraph("When a Tree inside a Grid Detail changes it's vertical size (open, close a Node) the sizing of the Detail is corrupt. When e.g. pressing (Ctrl)-(+) and/or (Ctrl)-(-) to change broswer size the Grid Detail will be correctly sized afterwards. Should be sized correctly direct after inner size change.ionaly this should also work inside a dialog, in the example there are two dialogs, one with only a div and paragraphs inide the details (static, so no resizing can happen later) and one with the tree inside the details where the resize makes problems. The Vaadin Version 14.4.5 additionally had the problem that the tree inside the details was not displayed entirely and when trying there were dozents of js errors (you can try when switching the example to 14.4.5). Up to 14.4.4 and again from 14.4.6 this behavior did not apply."));
		add(new Paragraph("There is another Problem with the itemDoubleClick events: When the detail is expanded we want the coresponding item of the grid to be selected. When we do that and then double click a node of the tree in the details component both double click events of the treegid in the details and the parent grid are fired. If the user selects another node or selects the same node this problem does not occur"));

		add(new H2("PROBLEM 2 : Vaadin Version: 14.4.7"));
		add(new RouterLink("Problem in Scrolling to Index in TreeGrid", Dispatcher.class, "TreeGridScrolltoItem"));
		add(new Paragraph("When a hierarchical TreeGrid is expanded and it is necessary to scroll to an item, that doesn't work, because of the lazy loading. HOW to reproduce: clicking on the Button 'scroll to <TEST>' should scoll to the item <TEST>. But it doesn't (if nodes are expanded). When manually scolling from the end to the beginning and clicking on the button again - it will scroll correctly (maybe after a few tries). Or if the tree is collapsed (recursivly). In the console the NodeIndex of the node is shown, but it doesn't correspond to the rowindex (NodeIndex-1) within the DOM!"));
		add(new Paragraph("HINT: if the _cache.effectiveSize of the grid is lower than the index of the node to scroll, the node is not reachable. For test a eclipse-console log entry is created which shows the (scrollTo)index and the _cache.effectiveSize value."));
	} 
}
