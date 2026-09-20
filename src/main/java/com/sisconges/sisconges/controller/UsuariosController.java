package com.sisconges.sisconges.controller;

import com.sisconges.sisconges.model.Usuarios;
import com.sisconges.sisconges.repository.UsuariosRepository;
import com.sisconges.sisconges.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public List<Usuarios> getAll()
    {
        return usuariosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Usuarios getById(@PathVariable Long id)
    {
        return usuariosRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Usuarios create(@RequestBody Usuarios usuarios)
    {
        return usuariosService.guardar(usuarios);
    }

    @PutMapping("/{id}")
    public Usuarios update(@PathVariable Long id, @RequestBody Usuarios usuarios)
    {
        usuarios.setId_usuarios(id);

        return usuariosService.guardar(usuarios);
    }

    @PutMapping("/{id}/estado")
    public Usuarios cambiarEstado(@PathVariable Long id)
    {
        Usuarios usuarios = usuariosRepository.findById(id).orElse(null);

        if (usuarios != null)
        {
            if ("Activo".equalsIgnoreCase(usuarios.getEstado()))
            {
                usuarios.setEstado("Inactivo");
            }
            else
            {
                usuarios.setEstado("Activo");
            }

            return usuariosRepository.save(usuarios);
        }
        return null;
    }
}
