package com.ylb.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ylb.Mapper.TypeMapper;
import com.ylb.Pojo.Blog;
import com.ylb.Pojo.Type;
import com.ylb.Service.BlogService;
import com.ylb.Service.TagService;
import com.ylb.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    BlogService blogService;

    @Autowired
    TagService tagService;

    @Autowired
    TypeService typeService;


    @GetMapping(value = {"/", "/index"})
    public String index(@RequestParam(defaultValue = "1", required = false) Integer pageNum, Map<String, Object> map) {

        map.put("page", blogService.getByPublished(pageNum, 10));
        map.put("types", typeService.getTypeLimit());
        map.put("tags", tagService.getTagLimit());
        map.put("recommened", blogService.getRecommened());
        return "index";
    }

    @GetMapping("/footer/newblog")
    public String newBlog(Map<String, Object> map) {
        List<Blog> footerBlogList = blogService.getFooterBlogList();
        map.put("newblogs", footerBlogList);
        return "_fragments :: newblogList";
    }

}
