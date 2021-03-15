package com.generali.fipos.vaadin.problems;

import java.util.List;

import com.generali.fipos.vaadin.data.MapNode;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.hierarchy.TreeData;
import com.vaadin.flow.data.provider.hierarchy.TreeDataProvider;
import com.vaadin.flow.data.renderer.TemplateRenderer;

@SuppressWarnings("serial")
public class TreeGridScrolltoItem<T> extends VerticalLayout {

	public TreeGridScrolltoItem() {
		testTree("treegridtest");
	}

	private void testTree(String id) {
		TreeGrid<MapNode> tree = new TreeGrid<>();
		tree.setId(id);
		tree.setClassName("mappingtree");
		tree.setWidth("460px"); 
		tree.setHeight("260px"); 
		List<MapNode> rootItems = MapNode.buildSample(30,3, x ->   x+1);
		List<MapNode> rootItems1 = MapNode.buildSample(30,3, x ->   x+1);
		tree.setDataProvider((DataProvider<MapNode, ?>)	new TreeDataProvider<>(new TreeData<MapNode>().addItems(rootItems, 	md -> md.getChildren())));
		tree.addColumn(getRenderer());
		// tree.addHierarchyColumn(item -> item.getName());
		MapNode _node = new MapNode("<TEST>");
		tree.getTreeData().addRootItems(_node);
		tree.getTreeData().addRootItems(rootItems1);
		tree.addExpandListener(event -> {
			System.out.println("Expanded Event fired"); 
		});
		tree.setPageSize(10000);
		add(tree);
		tree.expandRecursively(rootItems, 1);
		Button button1 = new Button("scroll to Node <TEST>");
		button1.addClickListener(e-> {
			tree.deselect(_node);
			int _index = tree.getDataCommunicator().getIndex(_node);
			System.out.println("Scroll to Node with index: "+_index); 
			tree.scrollToIndex(_index);
			tree.select(_node);
		});
		Button button2 = new Button("collapse all Nodes");
		button2.addClickListener(e-> {
			tree.collapseRecursively(rootItems, 10);
		});
		Button button3 = new Button("expand all Nodes");
		button3.addClickListener(e-> {
			tree.expandRecursively(rootItems, 10);
		});

		add(button1,button2,button3);
	}

	private TemplateRenderer<MapNode> getRenderer() {
		TemplateRenderer<MapNode> _r = TemplateRenderer
				.<MapNode> of("<vaadin-grid-tree-toggle data-id$='[[item.id]]' leaf='[[item.leaf]]' expanded='{{expanded}}' level='[[level]]'></vaadin-grid-tree-toggle><gli-icon class='configicon' style$='[[item.iconStyle]]' srcfiles='[[item.icon]]'></gli-icon><span style$='[[item.textStyle]]'>[[item.name]]</span>")
				.withProperty("id", item -> item.getId()) //			
				.withProperty("leaf", item -> item.getChildren().isEmpty()) //
				.withProperty("name", item -> item.toString());
		return _r;
	}

}
