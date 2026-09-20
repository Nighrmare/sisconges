package com.sisconges.sisconges.view;

import com.sisconges.sisconges.model.Usuarios;
import com.sisconges.sisconges.repository.UsuariosRepository;
import com.sisconges.sisconges.service.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuariosView
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/view/usuarios")
    public String lista(Model model)
    {
        model.addAttribute(
                "usuarios",
                usuariosRepository.findAll()
        );

        return "usuarios/list";
    }

    @PostMapping("/view/usuarios/save")
    public String save(
            @Valid @ModelAttribute("usuarios") Usuarios usuarios,
            BindingResult result,
            RedirectAttributes ra)
    {
        if (result.hasErrors())
        {
            ra.addAttribute(
                    "message",
                    "No se pudo guardar el usuario. Verifique los datos ingresados."
            );

            return "redirect:/view/usuarios";
        }

        boolean nuevo = usuarios.getId_usuarios() == null;

        usuariosService.guardar(usuarios);

        if (nuevo)
        {
            ra.addAttribute( "message", "Usuario creado correctamente. El usuario ha sido registrado en el sistema.");
        }
        else
        {
            ra.addAttribute( "message", "Usuario actualizado correctamente.");
        }

        return "redirect:/view/usuarios";
    }

    @PostMapping("/view/usuarios/toggle/{id}")
    public String toggleEstado(@PathVariable Long id, RedirectAttributes ra)
    {
        Usuarios usuarios = usuariosRepository.findById(id).orElse(null);

        if (usuarios != null)
        {
            if ("Activo".equalsIgnoreCase(usuarios.getEstado()))
            {
                usuarios.setEstado("Inactivo");
                ra.addAttribute("message", "Usuario desactivado con exito");
            }
            else
            {
                usuarios.setEstado("Activo");
                ra.addAttribute("message", "Usuario activado con exito");
            }

            usuariosRepository.save(usuarios);
        }

        return "redirect:/view/usuarios";
    }
}
