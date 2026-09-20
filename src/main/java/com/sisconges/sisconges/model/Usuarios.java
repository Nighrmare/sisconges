package com.sisconges.sisconges.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuarios
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_usuarios;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(min = 2, max = 50, message = "Los nombres deben tener entre 2 y 50 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 2, max = 50, message = "Los apellidos deben tener entre 2 y 50 caracteres")
    private String apellidos;

    @Column(unique = true)
    private String usuario;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Ingrese un correo electrónico válido")
    private String correo;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipo_documento;

    @NotBlank(message = "El numero de documento es obligatorio")
    @Size(min = 5, max = 20, message = "El número de documento debe tener entre 5 y 20 caracteres")
    private String numero_documento;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fecha_nacimiento;

    private String contrasena;

    private String rol;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

}
