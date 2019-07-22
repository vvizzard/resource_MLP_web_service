package org.mlp.apps.metadata;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.mlp.apps.base.BaseEntity;
import org.mlp.apps.photo.Photo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="metadata")
public class Metadata extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7449507556703779385L;

	@Column(name = "url", insertable = true, updatable = true, length = 400)
    private String url;

    @Column(name = "coverage", insertable = true, updatable = true, length = 400)
    private String coverage;

    @Column(name = "description", insertable = true, updatable = true, length = 400)
    private String description;

    @Column(name = "language", insertable = true, updatable = true, length = 400)
    private String language;

    @Column(name = "relation", insertable = true, updatable = true, length = 400)
    private String relation;

    @Column(name = "source", insertable = true, updatable = true, length = 400)
    private String source;

    @Column(name = "subject", insertable = true, updatable = true, length = 400)
    private String subject;

    @Column(name = "title", insertable = true, updatable = true, length = 400)
    private String title;

    @Column(name = "date_publication", insertable = true, updatable = true)
    private String date;

    @Column(name = "year", insertable = true, updatable = true)
    private String year;

    @Column(name = "format", insertable = true, updatable = true)
    private String format;

    @Column(name = "file_format", insertable = true, updatable = true)
    private String fileFormat;

    @Column(name = "identifier", insertable = true, updatable = true, length = 400)
    private String identifier;

    @Column(name = "type", insertable = true, updatable = true, length = 400)
    private String type;

    @Column(name = "contributor", insertable = true, updatable = true, length = 400)
    private String contributor;

    @Column(name = "creator", insertable = true, updatable = true, length = 400)
    private String creator;

    @Column(name = "publisher", insertable = true, updatable = true, length = 400)
    private String publisher;

    @Column(name = "rights", insertable = true, updatable = true, length = 400)
    private String rights;

    @Column(name = "id_document", insertable = true, updatable = true)
    private Integer idDocument;

    @Column(name = "id_utilisateur", insertable = true, updatable = true)
    private Integer idUtilisateur;

    @Column(name = "bibliographic_resource", insertable = true, updatable = true, length = 400)
    private String bibliographicResource;
    
    @JsonIgnore
    @OneToMany(mappedBy = "metadata", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<Photo> photos = null;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCoverage() {
		return coverage;
	}

	public void setCoverage(String coverage) {
		this.coverage = coverage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContributor() {
		return contributor;
	}

	public void setContributor(String contributor) {
		this.contributor = contributor;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	public Integer getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(Integer idDocument) {
		this.idDocument = idDocument;
	}

	public Integer getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Integer idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getBibliographicResource() {
		return bibliographicResource;
	}

	public void setBibliographicResource(String bibliographicResource) {
		this.bibliographicResource = bibliographicResource;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
    
    
}
