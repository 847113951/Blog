package com.ylb.Controller;

import com.ylb.Pojo.Blog;
import com.ylb.Pojo.Type;
import com.ylb.Service.BlogService;
import com.ylb.Service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class TypeController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PathVariable("id") Long id,
                        @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                        Map<String, Object> map) {
        List<Type> types = typeService.getTypeAndCountBlog();
        if (id == -1) {
            id = types.get(0).getId();
        }
        Blog blog = new Blog();

        map.put("types", types);
        map.put("blog", blogService.getByTypeId(pageNumber, 10, id));
        map.put("active", id);
        return "types";
    }
}
