package com.week4.week4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.week4.week4.entities.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
