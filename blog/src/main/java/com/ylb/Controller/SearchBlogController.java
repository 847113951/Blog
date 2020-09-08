package com.ylb.Controller;

import com.github.pagehelper.PageInfo;
import com.ylb.Pojo.Blog;
import com.ylb.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class SearchBlogController {
    @Autowired
    BlogService blogService;


    @GetMapping("/search")
    public String search(@RequestParam(defaultValue = "1", required = false) Integer pageNum,  String query, Map<String, Object> map) {
        System.out.println(query);
        PageInfo<Blog> blogs = blogService.search(pageNum, 10, query);
        map.put("page", blogs);
        map.put("query", query);
        return "search";
    }

}
