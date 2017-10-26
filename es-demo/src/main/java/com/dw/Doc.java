package com.dw;

public class Doc {
	
	private Integer id;
	private String name;
	private String type;
	private String author;
	public Doc(){
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Doc(Integer id, String name, String type, String author) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.author = author;
	}
	

}
