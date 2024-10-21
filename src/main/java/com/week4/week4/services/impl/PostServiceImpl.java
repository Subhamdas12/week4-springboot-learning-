package com.week4.week4.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.week4.week4.dtos.PostDTO;
import com.week4.week4.entities.PostEntity;
import com.week4.week4.exceptions.ResourceNotFoundException;
import com.week4.week4.repositories.PostRepository;
import com.week4.week4.services.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostDTO postDto) {
        PostEntity postEntity = modelMapper.map(postDto, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostEntity> allPostEntity = postRepository.findAll();
        return allPostEntity.stream().map(postsEntity -> modelMapper.map(postsEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO updatePost(PostDTO postDto, Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("No post found with id : " + postId));
        postDto.setId(postId);
        modelMapper.map(postDto, postEntity);
        PostEntity savedPostEntity = postRepository.save(postEntity);
        return modelMapper.map(savedPostEntity, PostDTO.class);

    }

}
