package com.ylb.Controller.admin;

import com.github.pagehelper.PageInfo;
import com.ylb.Pojo.Tag;
import com.ylb.Service.TagService;
import com.ylb.Util.MD5;
import com.ylb.Util.MyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.websocket.server.PathParam;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class HtTagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tag")//获取标签数据
    public String toTag(@RequestParam(required = false, defaultValue = "1") Integer pageNumber, Map<String, Object> map) {
        PageInfo<Tag> tagList = tagService.getTagList(pageNumber, 5);
        map.put("tag", tagList);
        return "ht/htTags";
    }


    @GetMapping("/tag/editTag")//进入添加页面
    public String toEdit() {
        return "ht/tagInput";
    }

    @GetMapping("/tag/editTag/{id}")//进入编辑页面
    public String toEdit1(@PathVariable("id") Long id, Map<String, Object> map) {
        Tag tagById = tagService.getTagById(id);
        map.put("tag", tagById);
        return "ht/tagInput";
    }


    @PostMapping("/tag")//添加标签
    public String add(Tag tag, Map<String, Object> map, RedirectAttributes redirectAttributes) {
        String validate = MyValidator.validate(tag);
        if (validate != null) {
            map.put("message", "添加【" + tag.getName() + "】失败，" + validate);
            return "ht/tagInput";
        }
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null) {
            map.put("message", "添加【" + tag.getName() + "】失败，该标签已经存在");
            return "ht/tagInput";
        }
        int i = tagService.saveTag(tag);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("message", "添加【" + tag.getName() + "】成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "添加【" + tag.getName() + "】失败");
        }
        return "redirect:/admin/tag";
    }

    @PutMapping("/tag")//修改标签
    public String EditTag(Tag tag, RedirectAttributes redirectAttributes, Map<String, Object> map) {
        String validate = MyValidator.validate(tag);
        if (validate != null) {
            map.put("message", "修改【" + tag.getName() + "】失败，" + validate);
            return "ht/tagInput";
        }
        Tag tagByName = tagService.getTagByName(tag.getName());
        if (tagByName != null) {
            map.put("message", "修改【" + tag.getName() + "】失败，该标签已经存在");
            return "ht/tagInput";
        }
        int i = tagService.updateTag(tag);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("message", "更新【" + tag.getName() + "】成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "更新【" + tag.getName() + "】失败");
        }
        return "redirect:/admin/tag";
    }

    //@DeleteMapping("/tag/{id}")//删除标签
    @RequestMapping(value = "/tag/{id}",method = RequestMethod.DELETE)
    public String deleteTag(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        int i = tagService.removeTagById(id);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("message", "删除" + id + "成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "删除" + id + "失败");
        }
        return "redirect:/admin/tag";
    }

}
