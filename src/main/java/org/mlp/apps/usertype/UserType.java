package org.mlp.apps.usertype;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.mlp.apps.user.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="typeuser")
public class UserType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6813530063889432936L;
	
	@Id
	private Integer id;
	
	@Column(name="libelle")
	private String libelle;
	
	@Column(name="description")
	private String description;
	
	@JsonIgnore
	@ManyToMany(mappedBy="roles", fetch = FetchType.LAZY)
	private Set<User> users;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}