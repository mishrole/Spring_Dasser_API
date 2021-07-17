package com.dasser.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SearchUser implements Serializable {
	
	@Id
	private Integer id;
	private String lastname;
	private String firstname;
	private String login;
	private Status status;
	private static final long serialVersionUID = 1L;
	
}
