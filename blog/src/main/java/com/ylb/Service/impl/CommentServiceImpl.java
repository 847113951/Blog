package com.ylb.Service.impl;

import com.ylb.Mapper.CommentMapper;
import com.ylb.Pojo.Comment;
import com.ylb.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    private List<Comment> comments = new ArrayList<>();

    @Override
    public List<Comment> getCommentByBlogId(Long blogId) {


        //一级评论
        List<Comment> commentByBlogId = commentMapper.getCommentByBlogId(blogId, (long) -1);
        //循环找出一级评论的所有子评论并放到集合中
        for (Comment comment : commentByBlogId) {

            String parentName = comment.getNickname();
            Long id = comment.getId();
            List<Comment> childComments = commentMapper.findByParentIdNotNull(id);
            commentChildren(childComments, parentName);
            comment.setReplyComment(comments);
            comments = new ArrayList<>();
        }
        return commentByBlogId;
    }

    private void commentChildren(List<Comment> childComments, String parentName) {
        if (childComments.size() > 0) {
            for (Comment comment : childComments) {
                String name = comment.getNickname();
                comment.setParentNickName(parentName);
                comments.add(comment);
                Long childid = comment.getId();
                recursively(childid, name);
            }
        }
    }

    private void recursively(Long childid, String name) {
        List<Comment> replayComment = commentMapper.findByParentIdNotNull(childid);

        if (replayComment.size() > 0) {
            for (Comment comment : replayComment) {
                String parentName = comment.getNickname();
                comment.setParentNickName(name);
                Long replayId = comment.getId();
                comments.add(comment);
                recursively(replayId, parentName);
            }
        }
    }


    @Override
    public int saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        System.out.println("service层 comment数据" + comment.getAvatar());
        return commentMapper.insertSelective(comment);
    }

    @Override
    public Integer deleteCommentByBlogId(Long id) {
        return commentMapper.deleteCommentByBlogId(id);
    }
}
