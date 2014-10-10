package com.wolfbeisz.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.wolfbeisz.model.Comment;
import com.wolfbeisz.model.Document;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	public Collection<Comment> findByDocument(Document document);
}
