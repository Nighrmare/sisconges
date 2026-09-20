package com.sisconges.sisconges.view;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutView
{
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session)
    {
        session.invalidate();

        return "redirect:/login";
    }
}