package com.example.duangiatsay.service;

import com.example.duangiatsay.model.Post;

import java.util.List;

public interface IPostService {
    Post createPost(Post post, String username);
    Post updatePost(Long id, Post post);
    void deletePost(Long id);
    Post getPostById(Long id);
    List<Post> getAllPosts();
}