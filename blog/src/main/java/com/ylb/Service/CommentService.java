package com.ylb.Service;

import com.ylb.Pojo.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getCommentByBlogId(Long BlogId);

    int saveComment(Comment comment);

    Integer deleteCommentByBlogId(Long id);
}
