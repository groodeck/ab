package org.ab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Users")
public class User {

	@Id
	@Column(name="userId")
	private Integer userId;

	@Column(name="name")
	private String name;

	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

}
