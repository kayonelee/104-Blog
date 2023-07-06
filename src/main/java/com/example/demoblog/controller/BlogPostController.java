package com.example.demoblog.controller;

import com.example.demoblog.model.BlogPost;
import com.example.demoblog.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class BlogPostController {
    private final BlogPostService blogPostService;

    @Autowired
    public BlogPostController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }

    @GetMapping("")
    public String getAllBlogPosts(Model model) {
        List<BlogPost> blogPosts = blogPostService.getAllBlogPosts();
        model.addAttribute("blogPosts", blogPosts);
        return "blog_list";
    }

    @GetMapping("/{id}")
    public String getBlogPostById(@PathVariable("id") Long id, Model model) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if (blogPost != null) {
            model.addAttribute("blogPost", blogPost);
            return "blog_post";
        } else {
            return "blog_error";
        }
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("blogPost", new BlogPost());
        return "blog_create";
    }

    @PostMapping("")
    public String createBlogPost(@ModelAttribute("blogPost") BlogPost blogPost) {
        blogPostService.createBlogPost(blogPost);
        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if (blogPost != null) {
            model.addAttribute("blogPost", blogPost);
            return "blog_edit";
        } else {
            return "blog_error";
        }
    }

    @PostMapping("/{id}")
    public String updateBlogPost(@PathVariable("id") Long id, @ModelAttribute("blogPost") BlogPost updatedBlogPost) {
        BlogPost existingBlogPost = blogPostService.getBlogPostById(id);
        if (existingBlogPost != null) {
            existingBlogPost.setTitle(updatedBlogPost.getTitle());
            existingBlogPost.setContent(updatedBlogPost.getContent());
            existingBlogPost.setAuthor(updatedBlogPost.getAuthor());
            blogPostService.updateBlogPost(existingBlogPost);
            return "redirect:/posts";
        } else {
            return "blog_error";
        }
    }

    @GetMapping("/{id}/delete")
    public String deleteBlogPost(@PathVariable("id") Long id) {
        blogPostService.deleteBlogPost(id);
        return "redirect:/posts";
    }

}

//THIS CLASS HANDLES HTTP REQUESTS AND CALL THE APPROPRIATE METHODS FROM
//THE BLOGPOSTSERVICE. IT SUPPORTS:
//RETURN ALL BLOG POST
//CREATING NEW BLOG POST
//RETURN BLOG POST BY SPECIFIED ID
//UPDATE BLOG POST BY SPECIFIED ID
//DELETE BLOG POST BY SPECIFIED ID