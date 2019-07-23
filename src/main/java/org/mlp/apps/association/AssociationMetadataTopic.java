/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mlp.apps.association;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "association_metadata_topic")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "id")),
    @AttributeOverride(name = "id1", column = @Column(name = "id_metadata")),
    @AttributeOverride(name = "id2", column = @Column(name = "id_topic"))    
})
public class AssociationMetadataTopic extends BaseAssociation implements Serializable {
    
}
