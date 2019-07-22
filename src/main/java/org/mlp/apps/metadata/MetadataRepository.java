package org.mlp.apps.metadata;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MetadataRepository extends JpaRepository<Metadata, Integer> {

	public List<Metadata> findByType(String type);
}
