package com.wolfbeisz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wolfbeisz.model.Document;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

	@Query(value="Select d from Document d where d.title LIKE %?1%")
	public Collection<Document> findByTitle(String title);
}
