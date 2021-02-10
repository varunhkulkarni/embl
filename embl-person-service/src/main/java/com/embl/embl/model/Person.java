package com.embl.embl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Model for the Person entity
 * 
 * @author Varun-Kulkarni
 *
 */
@Entity
@Table(name = "person")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;

	private Integer age;

	private String colour;

	public Person() {
	}

	public Person(Long id, String firstName, String lastName, Integer age, String color) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.colour = color;
	}

	public Person(String firstName, String lastName, Integer age, String color) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.colour = color;
	}

	@ApiModelProperty(example = "1", value = "Id", dataType = "java.lang.Integer", readOnly = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonProperty("first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("age")
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@JsonProperty("favourite_colour")
	public String getColor() {
		return colour;
	}

	public void setColor(String color) {
		this.colour = color;
	}

	private Person(PersonBuilder builder) {
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.age = builder.age;
		this.colour = builder.color;
	}

	public static class PersonBuilder {

		private String firstName;
		private String lastName;
		private Integer age;
		private String color;

		public PersonBuilder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public PersonBuilder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public PersonBuilder setAge(Integer age) {
			this.age = age;
			return this;
		}

		public PersonBuilder setColor(String color) {
			this.color = color;
			return this;
		}

		public Person build() {
			return new Person(this);
		}
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", colour="
				+ colour + "]";
	}
	
	

}
