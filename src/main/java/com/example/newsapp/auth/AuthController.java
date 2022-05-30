package com.example.newsapp.auth;

import com.example.newsapp.user.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.WebAttributes;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class AuthController {

    private final AppUserService userService;



    @PostMapping("/login/error")
    public RedirectView errorLogin(HttpServletRequest request, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("status", 0);
        redirectAttributes.addFlashAttribute("message", "Incorrect Username or Password");
        return new RedirectView("/");
    }

    @PostMapping("/register")
    public RedirectView registerUser(@ModelAttribute AuthRegisterTemplate registerTemplate,
                                     RedirectAttributes redirectAttributes) {

        System.out.println(registerTemplate);
        Pair<Integer, String> result = userService.registerUser(registerTemplate);
        redirectAttributes.addFlashAttribute("status", result.getFirst());
        redirectAttributes.addFlashAttribute("message", result.getSecond());
        return new RedirectView("/");
    }


    @GetMapping
    public ModelAndView getHomePage(@ModelAttribute("flashAttribute") Object flashAttributes) {
        ModelAndView authView = new ModelAndView("auth");
        authView.addObject("login_template", new AuthLoginTemplate());
        authView.addObject("register_template", new AuthRegisterTemplate());
        return authView;
    }
}
