package com.sisconges.sisconges.repository;

import com.sisconges.sisconges.model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>
{
    @Query("SELECT COUNT(u) > 0 FROM Usuarios u WHERE u.usuario = :usuario")
    boolean existePorUsuario(@Param("usuario") String usuario);
}