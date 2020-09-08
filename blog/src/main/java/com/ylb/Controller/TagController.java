package com.ylb.Controller;

import com.ylb.Pojo.Blog;
import com.ylb.Pojo.Tag;
import com.ylb.Service.BlogService;
import com.ylb.Service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String types(@PathVariable("id") Long id,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                        Map<String, Object> map) {
        List<Tag> tags = tagService.getTags();
        if (id == -1) {
            id = tags.get(0).getId();
        }
        Blog blog = new Blog();
        map.put("tags", tags);
        map.put("blog", blogService.getByTagId(pageNumber, 10, id));
        map.put("active", id);
        return "tags";
    }

}
