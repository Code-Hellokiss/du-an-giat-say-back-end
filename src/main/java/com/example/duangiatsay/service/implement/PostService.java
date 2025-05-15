package com.example.duangiatsay.service.implement;

import com.example.duangiatsay.model.Post;
import com.example.duangiatsay.model.PostImage;
import com.example.duangiatsay.repository.PostRepository;
import com.example.duangiatsay.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService implements IPostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post createPost(Post post, String username) {
        post.setCreatedAt(LocalDateTime.now());

        if (post.getImages() != null) {
            for (PostImage image : post.getImages()) {
                image.setPost(post);
            }
        }

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Long id, Post updatedPost) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        post.setTitle(updatedPost.getTitle());
        post.setContent(updatedPost.getContent());

        post.getImages().clear();
        if (updatedPost.getImages() != null) {
            for (PostImage image : updatedPost.getImages()) {
                image.setPost(post);
            }
            post.getImages().addAll(updatedPost.getImages());
        }

        return postRepository.save(post);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }
    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

}