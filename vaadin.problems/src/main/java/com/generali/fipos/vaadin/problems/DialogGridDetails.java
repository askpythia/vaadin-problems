package com.generali.fipos.vaadin.problems;

import com.generali.fipos.vaadin.data.Item;
import com.generali.fipos.vaadin.data.Item.Detail;
import com.vaadin.componentfactory.EnhancedDialog;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.CollapseEvent;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.renderer.ComponentRenderer;

@SuppressWarnings("serial")
public class DialogGridDetails extends VerticalLayout {

	public DialogGridDetails() {
		HorizontalLayout buttons = new HorizontalLayout();
		Button button_ok = new Button("Open Dialog (OK)");
		button_ok.addClickListener(event -> getDialog(true).open());
		buttons.add(button_ok);
		Button button_problem = new Button("Open Dialog (Problem)");
		button_problem.addClickListener(event -> getDialog(false).open());
		buttons.add(button_problem);
		add(buttons);
		add(getGrid(false));
	}

	private Dialog getDialog(boolean ok) {
		var dialog = new EnhancedDialog();
		dialog.setCloseOnEsc(true);
		dialog.setCloseOnOutsideClick(true);
		dialog.setResizable(true);
		dialog.setDraggable(true);
		dialog.setWidth("60vw");
		dialog.setContent(getGrid(ok));
		return dialog;
	}

	private Component getGrid(boolean ok) {
		Grid<Item> grid = new Grid<>();

		grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_COMPACT, GridVariant.LUMO_ROW_STRIPES);
		grid.setWidthFull();
		grid.setHeightByRows(true);
		grid.setDetailsVisibleOnClick(false);

		grid.setItems(Item.samples(20));

		grid.addColumn(item -> item.getName()).setHeader("Name");
		grid.addColumn(new ComponentRenderer<>(item -> {
			Button button = new Button(grid.isDetailsVisible(item) ? VaadinIcon.MINUS.create() : VaadinIcon.PLUS.create());
			button.addClickListener(e -> {
				grid.setDetailsVisible(item, !grid.isDetailsVisible(item));
				grid.select(item);
			});
			button.setHeightFull();
			return button;
		})).setFlexGrow(0).setAutoWidth(false).setWidth("60px").setHeader("Details");
		grid.addItemDoubleClickListener(e -> Notification.show("Grid: ItemDoubleClickEvent on " + e.getItem().getName()));

		grid.setItemDetailsRenderer(new ComponentRenderer<Component, Item>(item -> getItemDetailsComponent(item, ok, grid)));

		return grid;
	}

	private Component getItemDetailsComponent(Item item, boolean ok, Grid<?> parent) {
		if(ok) {
			Div div = new Div();
			item.getDetails().forEach(detail -> {
				Paragraph paragraph = new Paragraph();
				paragraph.setText(detail.getDetail());
				div.add(paragraph);
			});
			return div;
		} else {
			TreeGrid<Detail> grid = new TreeGrid<>();
	
			grid.addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS, GridVariant.LUMO_COMPACT, GridVariant.LUMO_ROW_STRIPES);
			grid.setWidthFull();
			grid.setHeightByRows(true);
			
			grid.setItems(item.getDetails(), detail -> detail.getChildren());
			
			grid.addCollapseListener(event -> {
				resize(parent, grid, event);
			});

			grid.addExpandListener(event -> {
				resize(parent, grid, event);
			});

			grid.addHierarchyColumn(detail -> detail.getDetail()).setHeader("Details");
			
			grid.addItemDoubleClickListener(e -> Notification.show("Detail-Treegrid: itemDoubleClickEvent on " + e.getItem().getDetail()));

			return grid;
		}
	}

	private void resize(Grid<?> parent, TreeGrid<?> tree, ComponentEvent<?> event) {
		System.out.println("1" + event);
		//UI.getCurrent().getPage().executeJs("setTimeout(function() { $0._resizeHandler()}, 100);",parent);
		UI.getCurrent().getPage().executeJs("new ResizeObserver(e => $0._resizeHandler()).observe($1);", parent.getElement(), tree.getElement());
	}
}
