package org.mlp.apps.photo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.mlp.apps.base.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="photo_breakpoints")
public class PhotoBreakpoint extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1065540017478093553L;

	@Column(name = "link", insertable = true, updatable = true, length = 500)
    private String link;

    @Column(name = "img_size", insertable = true, updatable = true)
    private Integer size;
    
    @Column(name = "img_width", insertable = true, updatable = true)
    private Integer width;
    
    @Column(name = "img_height", insertable = true, updatable = true)
    private Integer height;

    @Column(name = "id_photo", insertable = true, updatable = true)
    private Integer idPhoto;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_photo", insertable = false, updatable = false)
    private Photo photo = null;

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

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getIdPhoto() {
		return idPhoto;
	}

	public void setIdPhoto(Integer idPhoto) {
		this.idPhoto = idPhoto;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
    
    
}
