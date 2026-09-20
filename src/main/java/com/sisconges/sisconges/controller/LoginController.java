package com.sisconges.sisconges.controller;

import com.sisconges.sisconges.config.AppSessionConfig;
import com.sisconges.sisconges.model.Usuarios;
import com.sisconges.sisconges.repository.UsuariosRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController
{
    @Autowired
    private AppSessionConfig appSessionConfig;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @PostMapping("/login")
    public String iniciarSesion(
            @RequestParam String usuario,
            @RequestParam String contrasena,
            HttpSession session,
            Model model)
    {
        Usuarios usuarios = usuariosRepository
                .buscarPorUsuario(usuario.trim())
                .orElse(null);

        if (usuarios == null)
        {
            model.addAttribute(
                    "error",
                    "El usuario o la contraseña no son correctos."
            );

            return "login/login";
        }

        if (!contrasena.equals(usuarios.getContrasena()))
        {
            model.addAttribute(
                    "error",
                    "El usuario o la contraseña no son correctos."
            );

            return "login/login";
        }

        if (!"Activo".equalsIgnoreCase(usuarios.getEstado()))
        {
            model.addAttribute(
                    "error",
                    "El usuario se encuentra inactivo."
            );

            return "login/login";
        }

        session.setAttribute(
                "usuarioId",
                usuarios.getId_usuarios()
        );

        session.setAttribute(
                "usuarioNombre",
                usuarios.getNombres() + " " + usuarios.getApellidos()
        );

        session.setAttribute(
                "usuario",
                usuarios.getUsuario()
        );

        session.setAttribute(
                "usuarioRol",
                usuarios.getRol()
        );

        session.setAttribute(
                "appInstanceId",
                appSessionConfig.getInstanceId()
        );

        return "redirect:/view/usuarios";
    }
}