package com.wolfbeisz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wolfbeisz.model.File;

public interface FileRepository extends CrudRepository<File, Long> {
	
	@Query("select f from File f where f.document.documentid = ?1 and f.version = (select max(f2.version) from File f2 where f2.document.documentid = ?1)")
	public File findLatest(long documentid);
}
