package com.wolfbeisz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.wolfbeisz.model.Change;

public interface ChangeRepository extends CrudRepository<Change, Long> {

	
//	@Query(value="Select chg.* from Document d inner join File f inner join Change chg where d.documentid = ?1 and d.user.userid = ?2")
	@Query(value="Select c from Document d, File f, Change c where d = f.document and f = c.file and d.documentid = ?1 and d.user.userid = ?2")
	public Collection<Change> findByDocumentId(long documentid, long userid);
	
	@Query(value="Select c from Document d, File f, Change c where d = f.document and f = c.file and d.documentid = ?1 and d.user.userid = ?2 and c.checkoutstamp is not null and c.checkinstamp is null")
	public Change findOpenChanges(long documentid, long userid);
}
