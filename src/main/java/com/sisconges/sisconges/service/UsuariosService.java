package com.sisconges.sisconges.service;

import com.sisconges.sisconges.model.Usuarios;
import com.sisconges.sisconges.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService
{
    @Autowired
    private UsuariosRepository usuariosRepository;

    public Usuarios guardar(Usuarios usuarios)
    {
        if (usuarios.getId_usuarios() == null)
        {
            usuarios.setUsuario(generarUsuario(usuarios));
        }
        else
        {
            Usuarios usuarioActual = usuariosRepository.findById(usuarios.getId_usuarios()).orElse(null);

            if (usuarioActual != null)
            {
                usuarios.setUsuario(usuarioActual.getUsuario());

                if (usuarios.getContrasena() == null || usuarios.getContrasena().isBlank())
                {
                    usuarios.setContrasena(usuarioActual.getContrasena());
                }
            }
        }

        return usuariosRepository.save(usuarios);
    }

    private String generarUsuario(Usuarios usuarios)
    {
        String nombres = quitarTildes(usuarios.getNombres().trim().toLowerCase());
        String apellidos = quitarTildes(usuarios.getApellidos().trim().toLowerCase());

        String[] nombresSeparados = nombres.split("\\s+");
        String[] apellidosSeparados = apellidos.split("\\s+");

        String usuario = nombresSeparados[0].substring(0, Math.min(3, nombresSeparados[0].length()));

        if (nombresSeparados.length > 1)
        {
            usuario += nombresSeparados[1].charAt(0);
        }

        usuario += apellidosSeparados[0].charAt(0);

        String usuarioBase = usuario;
        int numero = 2;

        while (usuariosRepository.existePorUsuario(usuario))
        {
            usuario = usuarioBase + numero;
            numero++;
        }

        return usuario;
    }

    private String quitarTildes(String texto)
    {
        return java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
}