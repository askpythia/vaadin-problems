package com.generali.fipos.vaadin.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import com.github.javafaker.Faker;

public class MapNode {

	private static Faker faker = new Faker();
	private static Function<Integer,Integer> idFunction = (x -> x);
	private static int count = 0;

	private int id;
	private String name;
	private List<MapNode> children = new ArrayList<>();

	public MapNode(String string) {
		setId(idFunction.apply(count++));
		setName(string);
	}

	private void setName(String string) {
		this.name=string;
	}

	private void setId(Integer id) {
		this.id=id;
	}

	public static List<MapNode> buildSample(int numberOfRootNodes, int depth, Function<Integer,Integer> idFunction) {
		count = 0;
		MapNode.idFunction = idFunction;
		return buildSample(numberOfRootNodes, depth);
	}

	public static List<MapNode> buildSample(int numberOfRootNodes, int depth) {
		List<MapNode> result = new ArrayList<>();
		for(int i = 0; i < numberOfRootNodes; i++) {
			result.add(new MapNode("").initialize(depth));
		}
		return result;
	}

	private MapNode initialize(int depth) {
		setId(idFunction.apply(count++));
		setName(faker.name().fullName());
		if(depth > 0) getChildren().addAll(buildSample(new Random().nextInt(10), depth-1));
		return this;
	}

	public List<MapNode> getChildren() {
		return this.children;
	}

	@Override
	public String toString() {
		return this.getName();
	}

	public String getName() {
		return this.name;
	}
	
	public int getId() {
		return this.id;
	}
}
