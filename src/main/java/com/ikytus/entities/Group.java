package com.ikytus.entities;

public class Group {

	private String id;
	private String tag;
	private String description;

	public Group() {
	}

	public Group(String id, String tag, String description) {
		this.id = id;
		this.tag = tag;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
