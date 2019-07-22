package org.mlp.apps.user;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.JoinColumn;

import org.mlp.apps.base.BaseEntity;
import org.mlp.apps.thematique.Thematique;
import org.mlp.apps.usertype.UserType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="utilisateur")
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7654738760426104071L;

	@Column(name="nom")
	private String nom;
	
	@Column(name="prenoms")
	private String prenom;
	
	@Column(name="titre")
	private String title;
	
	@Column(name="date_naissance")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateNaissance;
	
	@Column(name="date_inscription")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date dateInscription;
	
	@Column(name="institution")
	private String institution;
	
	@Column(name="post_occupe")
	private String postOccupe;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private Boolean enabled;
	
	@Column(name = "last_access_date")
	@Temporal(javax.persistence.TemporalType.DATE)
	private Date lastAccessDate;
	
	@Column(name = "email", nullable=false, unique=true)
	private String email;
	
	@Column(name = "biographie", columnDefinition = "text")
	private String biographie;
	
	@Column(name = "publication", columnDefinition = "text")
	private String publication;
	
	@Column(name = "photo_profil")
	private String photoProfil;
	
	@Column(name = "PROVIDER", nullable = false, length = 32)
	private String provider;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="utilisateur_typeuser", 
		joinColumns = {@JoinColumn(name="idutilisateur", referencedColumnName = "id")},
		inverseJoinColumns= {@JoinColumn(name = "idtypeuser", referencedColumnName = "id")}
	)
	private Set<UserType> roles;

	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="domaine_expertise", 
		joinColumns = {@JoinColumn(name="iduser", referencedColumnName = "id")},
		inverseJoinColumns= {@JoinColumn(name = "idthematique", referencedColumnName = "id")}
	)
	private Set<Thematique> dExpertise;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getPostOccupe() {
		return postOccupe;
	}

	public void setPostOccupe(String postOccupe) {
		this.postOccupe = postOccupe;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBiographie() {
		return biographie;
	}

	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public String getPhotoProfil() {
		return photoProfil;
	}

	public void setPhotoProfil(String photoProfil) {
		this.photoProfil = photoProfil;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public Set<UserType> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserType> roles) {
		this.roles = roles;
	}

	public Set<Thematique> getdExpertise() {
		return dExpertise;
	}

	public void setdExpertise(Set<Thematique> dExpertise) {
		this.dExpertise = dExpertise;
	}
	
	
}
