package org.mlp.apps.photo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.mlp.apps.base.BaseEntity;
import org.mlp.apps.metadata.Metadata;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="photo")
public class Photo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2234806374365987134L;

	@Column(name = "name", insertable = true, updatable = true, length = 100)
    private String name;
    
    @Column(name = "link", insertable = true, updatable = true, length = 500)
    private String link;

    @Column(name = "img_size", insertable = true, updatable = true)
    private Integer size;

    @Column(name = "id_metadata", insertable = true, updatable = true)
    private Integer idMetadata;
    
    @Column(name = "id_user", insertable = true, updatable = true)
    private Integer idUser;
    
    @Column(name = "id_post", insertable = true, updatable = true)
    private Integer idPost;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_metadata", insertable = false, updatable = false)
    @JsonBackReference
    private Metadata metadata = null;
    
    @OneToMany(mappedBy = "photo", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<PhotoBreakpoint> breakpoints = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getIdMetadata() {
		return idMetadata;
	}

	public void setIdMetadata(Integer idMetadata) {
		this.idMetadata = idMetadata;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdPost() {
		return idPost;
	}

	public void setIdPost(Integer idPost) {
		this.idPost = idPost;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public List<PhotoBreakpoint> getBreakpoints() {
		return breakpoints;
	}

	public void setBreakpoints(List<PhotoBreakpoint> breakpoints) {
		this.breakpoints = breakpoints;
	}
    
    
}
