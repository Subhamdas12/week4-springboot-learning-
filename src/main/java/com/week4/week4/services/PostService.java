package com.week4.week4.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.week4.week4.dtos.PostDTO;

public interface PostService {

    PostDTO createPost(PostDTO postDto);

    List<PostDTO> getAllPosts();

    PostDTO updatePost(PostDTO postDto, Long postId);

}
