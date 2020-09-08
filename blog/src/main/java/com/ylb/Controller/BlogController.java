package com.ylb.Controller;

import com.ylb.Pojo.Blog;
import com.ylb.Service.BlogService;
import com.ylb.Util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable("id") Long id, Map<String, Object> map) {
        Blog blog = blogService.getByPublishedById(id);
        blog.setContent(MarkdownUtils.markdownToHtmlExtensions(blog.getContent()));
        map.put("blog", blog);
        return "blog";
    }
}
