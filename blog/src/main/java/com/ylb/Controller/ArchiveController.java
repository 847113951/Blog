package com.ylb.Controller;

import com.ylb.Pojo.Blog;
import com.ylb.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchiveController {
    @Autowired
    BlogService blogService;


    @GetMapping("/archives")
    public String archive(Map<String,Object> map) {
        Map<String, List<Blog>> stringListMap = blogService.archiveBlog();
        map.put("count",blogService.getCountBlog());
        map.put("archive", stringListMap);
        return "archives";
    }
}
