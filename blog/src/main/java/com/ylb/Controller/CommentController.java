package com.ylb.Controller;

import com.ylb.Pojo.Comment;
import com.ylb.Pojo.User;
import com.ylb.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;
    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comment/{blogId}")
    public String getComment(@PathVariable("blogId") Long blogId, Map<String, Object> map) {
        List<Comment> commentByBlogId = commentService.getCommentByBlogId(blogId);
        System.out.println("控制层" + commentByBlogId);
        map.put("comments", commentByBlogId);
        return "blog :: commentList";
    }

    @PostMapping("/comment")
    public String post(Comment comment, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            comment.setIsUser(1);
            comment.setNickname(user.getNickname());
            comment.setAvatar(user.getAvater());
        } else {
            comment.setIsUser(0);
            comment.setAvatar(avatar);
        }

        int i = commentService.saveComment(comment);
        return "redirect:/comment/" + comment.getBlogId();

    }
}
