package com.wolfbeisz.repository;

import org.springframework.data.repository.CrudRepository;

import com.wolfbeisz.model.File;

public interface FileRepository extends CrudRepository<File, Long> {

}
