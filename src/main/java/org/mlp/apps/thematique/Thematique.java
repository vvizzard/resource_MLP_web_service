package org.mlp.apps.thematique;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.mlp.apps.base.BaseEntity;
import org.mlp.apps.user.User;

@Entity
@Table(name="thematique")
public class Thematique extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5918231537448490285L;
	
	@Column(name="libelle", unique=true, nullable=false)
	private String libelle;
	
	@Column(name="description", nullable=true)
	private String description;
	
	@Column(name="date_creation", nullable=false)
	private Date creationDate;
	
	@Column(name="date_modif", nullable=true)
	private Date editDate;
	
	@Column(name="created_by", nullable=true)
	private Integer createdById;
	
	@Column(name="modified_by", nullable=true)
	private Integer modifierById;

	@Column(name="deleted_by", nullable=true)
	private Integer deletedBy;
	
	@Column(name="deleted_date", nullable=true)
	private Date deletedDate;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name="domaine_expertise", 
		joinColumns = {@JoinColumn(name="idthematique", referencedColumnName = "id")},
		inverseJoinColumns= {@JoinColumn(name = "iduser", referencedColumnName = "id")}
	)
	private Set<User> managers;

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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getModifierById() {
		return modifierById;
	}

	public void setModifierById(Integer modifierById) {
		this.modifierById = modifierById;
	}

	public Integer getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(Integer deletedBy) {
		this.deletedBy = deletedBy;
	}

	public Date getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(Date deletedDate) {
		this.deletedDate = deletedDate;
	}

	public Set<User> getManagers() {
		return managers;
	}

	public void setManagers(Set<User> managers) {
		this.managers = managers;
	}
	
	
}
