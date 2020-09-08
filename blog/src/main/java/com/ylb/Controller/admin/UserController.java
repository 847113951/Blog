package com.ylb.Controller.admin;

import com.ylb.Pojo.User;
import com.ylb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String login() {
        return "ht/login";
    }


    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession httpSession, RedirectAttributes redirectAttributes) {
        User user = userService.checkUser(username, password);
        if (user != null) {
            user.setPassword(null);
            httpSession.setAttribute("user", user);
            return "ht/htIndex";
        }
        System.out.println("不为空");
        redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
        return "redirect:/admin";/*重定向不可以用map*/
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
