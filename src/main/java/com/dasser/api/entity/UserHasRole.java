package com.dasser.api.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_has_role")
public class UserHasRole implements Serializable {

	@EmbeddedId
	private UserHasRolePK userHasRolePK;
	
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private User user;
	
	@ManyToOne
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	@JoinColumn(name = "role_id", nullable = false, insertable = false, updatable = false)
	private Role role;
	
	private static final long serialVersionUID = 1L;

}
