package com.example.demoblog.repository;

import com.example.demoblog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}

//THIS GIVES THE BASIC CRUD OPERATIONS FOR THE BLOGPOST ENTITY.