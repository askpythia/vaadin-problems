package com.generali.fipos.vaadin.data;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

public class Item {

	public static class Detail {

		private String detail;
		private List<Detail> children = new ArrayList<>();

		public Detail() {
			detail = faker.hitchhikersGuideToTheGalaxy().quote();
		}

		public String getDetail() {
			return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

		public List<Detail> getChildren() {
			return children;
		}

		public void addChildren(int num) {
			for (int i = 0; i < num; i++) {
				children.add(new Detail());
			}
		}
	}

	private static Faker faker = new Faker();

	private String name;
	private List<Detail> details = new ArrayList<>();

	public Item() {
		setName(faker.hitchhikersGuideToTheGalaxy().character());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Detail> getDetails() {
		return details;
	}

	private Item details(int num) {
		for (int i = 0; i < num; i++) {
			Detail detail = new Detail();
			detail.addChildren(3);
			details.add(detail);
		}
		return this;
	}

	public static List<Item> samples(int num) {
		var list = new ArrayList<Item>();
		for (int i = 0; i < num; i++) {
			list.add(new Item().details(10));
		}
		return list;
	}
}
