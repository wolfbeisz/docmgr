package com.wolfbeisz.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.wolfbeisz.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
