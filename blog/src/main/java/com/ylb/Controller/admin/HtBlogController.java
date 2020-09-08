package com.ylb.Controller.admin;

import com.github.pagehelper.PageInfo;
import com.ylb.Pojo.Blog;
import com.ylb.Pojo.User;
import com.ylb.Service.BlogService;
import com.ylb.Service.TagService;
import com.ylb.Service.TypeService;
import com.ylb.Util.MD5;
import com.ylb.Util.MyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class HtBlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagService tagService;

    @GetMapping("/blog")//进入博客首页
    public String blog(@RequestParam(defaultValue = "1", required = false) Integer pageNum, Blog blog, Map<String, Object> map) {
        PageInfo<Blog> blogList = blogService.getBlogList(pageNum, 5, blog);
        map.put("type", typeService.getAllType());
        map.put("blog", blogList);
        return "ht/htBlog";
    }

    @PostMapping("/blog/search")//通过查询，实现局部的渲染
    public String search(@RequestParam(defaultValue = "1", required = false) Integer pageNum, Blog blog, Map<String, Object> map) {
        System.out.println("页码" + pageNum);
        System.out.println("blog" + blog);
        PageInfo<Blog> blogList = blogService.getBlogList(pageNum, 5, blog);
        map.put("blog", blogList);
        return "ht/htBlog :: blogList";
    }


    @GetMapping("/blog/edit")//跳转到博客编辑页面
    public String toEdit(Map<String, Object> map) {
        map.put("types", typeService.getAllType());
        map.put("tags", tagService.Tags());
        return "ht/BlogInput";
    }

    @GetMapping("/blog/edit/{id}")//跳转到博客编辑页面通过id查询一个信息并进行修改
    public String toEditById(@PathVariable("id") Long id, Map<String, Object> map) {
        map.put("types", typeService.getAllType());
        map.put("tags", tagService.Tags());
        Blog blogById = blogService.getBlogById(id);
        blogById.setTagIds(tagService.getTagsById(id));
        map.put("blog", blogById);
        return "ht/BlogEdit";

    }


    @PostMapping("/blog")
    public String post(Blog blog, RedirectAttributes redirectAttributes,
                       HttpSession httpSession, Map<String, String> map) {
        String validate = MyValidator.validate(blog);
        if (validate != null) {
            map.put("message", validate);
            return "ht/BlogInput";
        }
        User user = (User) httpSession.getAttribute("user");
        blog.setUserId(user.getId());
        blog.setType(typeService.getTypeById(blog.getTypeId()));
        blog.setTags(tagService.getTagsByIds(blog.getTagIds()));
        int b = blogService.addBlog(blog);
        if (b > 0) {
            redirectAttributes.addFlashAttribute("message", "添加博客【 "+blog.getTitle()+" 】成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "添加博客【 \"+blog.getTitle()+\" 】失败");
        }
        return "redirect:/admin/blog";
    }


    @DeleteMapping("/blog/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        System.out.println("删除id"+id);
        int i = blogService.deleteBlogById(id);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("message", "删除博客【 "+id+" 】成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "删除博客【 \"+id+\" 】失败");
        }
        return "redirect:/admin/blog";
    }

    @PutMapping("/blog")
    public String update(Blog blog, Map<String, Object> map, RedirectAttributes redirectAttributes,
                         HttpSession httpSession) {
        String validate = MyValidator.validate(blog);
        if (validate != null) {
            map.put("message", validate);
            return "ht/BlogEdit";
        }
        User user = (User) httpSession.getAttribute("user");
        blog.setUserId(user.getId());
        blog.setType(typeService.getTypeById(blog.getTypeId()));
        blog.setTags(tagService.getTagsByIds(blog.getTagIds()));
        int i = blogService.updateBlog(blog);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("message", "修改博客【 "+blog.getTitle()+" 】成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "修改博客【 "+blog.getTitle()+" 】失败");
        }
        return "redirect:/admin/blog";
    }


}
