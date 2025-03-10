package com.pluralsight.model;

public class Ride {

	private Integer id;
	private String name;
	private int duration;

	public int getDuration() {
		return duration;
	}

	public String getName() {
		return name;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Ride{" +
				"id=" + id +
				", name='" + name + '\'' +
				", duration=" + duration +
				'}';
	}
}
