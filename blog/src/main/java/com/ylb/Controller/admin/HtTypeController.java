package com.ylb.Controller.admin;

import com.github.pagehelper.PageInfo;
import com.ylb.Pojo.Type;
import com.ylb.Service.TypeService;
import com.ylb.Util.MyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class HtTypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("/type")//跳到主页
    public String type(@RequestParam(required = false, defaultValue = "1") Integer pageNumber, Map<String, Object> map) {
        int pageSize = 5;
        PageInfo<Type> typeList = typeService.getTypeList(pageNumber, pageSize);
        map.put("Type", typeList);
        return "ht/htTypes";
    }

    //跳转
    @GetMapping("/type/input")//跳转到添加页面
    public String ToAdd() {
        return "ht/typeInput";
    }


    @GetMapping("/type/{id}")//获得单个id信息，进入到编辑页面
    public String editType(@PathVariable("id") Long id, Map<String, Object> map) {
        Type typeById = typeService.getTypeById(id);
        map.put("type", typeById);
        return "ht/typeInput";
    }


    @PostMapping("/type")//编辑页面，添加类型信息
    public String add(Type type, RedirectAttributes redirectAttributes, Map<String, Object> map) {
        String validate = MyValidator.validate(type);
        Type typeByName = typeService.getTypeByName(type.getName());
        if (typeByName != null) {
            map.put("message", "该类型已经存在");
            return "ht/typeInput";
        }
        if (validate != null) {
            map.put("message", validate);
            return "ht/typeInput";
        }

        System.out.println(2);
        int i = typeService.saveType(type);

        if (i == 0) {
            redirectAttributes.addFlashAttribute("message", "添加失败");
        } else {
            redirectAttributes.addFlashAttribute("message", "添加成功");
        }

        return "redirect:/admin/type";
    }


    @PutMapping("/type")
    public String edit(Type type, RedirectAttributes redirectAttributes) {
        int i = typeService.updateType(type);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("message", "更新类型: "+type.getName()+"成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "更新失败");
        }
        return "redirect:/admin/type";
    }


    @DeleteMapping("/type/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        System.out.println(id);
        int i = typeService.removeTypeById(id);
        if (i > 0) {
            redirectAttributes.addFlashAttribute("message", "删除类型:"+id+"成功");
        } else {
            redirectAttributes.addFlashAttribute("message", "删除类型:"+id+"失败");
        }
        return "redirect:/admin/type";
    }
}



