function crearPopupSISCONGES()
{
    let popup = document.getElementById("popupSISCONGES");

    if (!popup)
    {
        popup = document.createElement("div");

        popup.id = "popupSISCONGES";
        popup.className = "sisconges-popup-overlay";

        popup.innerHTML = `
            <div class="sisconges-popup" role="dialog" aria-modal="true">
                
                <div class="sisconges-popup-icon" id="popupIcono">
                    !
                </div>

                <h2 id="popupTitulo">
                    Validación
                </h2>

                <p id="popupMensaje"></p>

                <div class="sisconges-popup-actions"
                     id="popupAcciones">
                </div>

            </div>
        `;

        document.body.appendChild(popup);
    }

    return popup;
}

function mostrarAlerta(titulo, mensaje, tipo = "warning")
{
    const popup = crearPopupSISCONGES();

    const icono = document.getElementById("popupIcono");
    const tituloElemento = document.getElementById("popupTitulo");
    const mensajeElemento = document.getElementById("popupMensaje");
    const acciones = document.getElementById("popupAcciones");

    icono.textContent = tipo === "success" ? "✓" : "!";
    tituloElemento.textContent = titulo;
    mensajeElemento.textContent = mensaje;

    acciones.innerHTML = "";

    const botonAceptar = document.createElement("button");

    botonAceptar.type = "button";
    botonAceptar.className = "sisconges-popup-btn";
    botonAceptar.textContent = "Aceptar";

    botonAceptar.addEventListener("click", cerrarPopupSISCONGES);

    acciones.appendChild(botonAceptar);

    popup.classList.add("show");
}

function mostrarConfirmacion( titulo, mensaje, textoConfirmar, accionConfirmar)
{
    const popup = crearPopupSISCONGES();

    const icono = document.getElementById("popupIcono");
    const tituloElemento = document.getElementById("popupTitulo");
    const mensajeElemento = document.getElementById("popupMensaje");
    const acciones = document.getElementById("popupAcciones");

    icono.textContent = "?";
    tituloElemento.textContent = titulo;
    mensajeElemento.textContent = mensaje;

    acciones.innerHTML = "";

    const botonCancelar = document.createElement("button");

    botonCancelar.type = "button";
    botonCancelar.className = "sisconges-popup-btn secondary";
    botonCancelar.textContent = "Cancelar";

    botonCancelar.addEventListener(
        "click",
        cerrarPopupSISCONGES
    );

    const botonConfirmar = document.createElement("button");

    botonConfirmar.type = "button";
    botonConfirmar.className = "sisconges-popup-btn";
    botonConfirmar.textContent = textoConfirmar;

    botonConfirmar.addEventListener("click", function()
    {
        cerrarPopupSISCONGES();

        if (typeof accionConfirmar === "function")
        {
            accionConfirmar();
        }
    });

    acciones.appendChild(botonCancelar);
    acciones.appendChild(botonConfirmar);

    popup.classList.add("show");
}

function cerrarPopupSISCONGES()
{
    const popup = document.getElementById("popupSISCONGES");

    if (popup)
    {
        popup.classList.remove("show");
    }
}

function abrirModalCrear()
{
    const modal = document.getElementById("modalUsuario");
    const form = document.getElementById("formUsuario");

    if (!modal || !form)
    {
        return;
    }

    form.reset();

    document.getElementById("id_usuarios").value = "";
    document.getElementById("usuario").value = "";

    document.getElementById("tituloModal").textContent =
        "Crear usuario";

    document.getElementById("textoModal").textContent =
        "Ingrese los datos del nuevo usuario";

    modal.classList.add("show");
}

function abrirModalEditar(boton)
{
    document.getElementById("id_usuarios").value =
        boton.dataset.id;

    document.getElementById("nombres").value =
        boton.dataset.nombres;

    document.getElementById("apellidos").value =
        boton.dataset.apellidos;

    document.getElementById("usuario").value =
        boton.dataset.usuario;

    document.getElementById("correo").value =
        boton.dataset.correo;

    document.getElementById("tipo_documento").value =
        boton.dataset.tipoDocumento;

    document.getElementById("numero_documento").value =
        boton.dataset.numeroDocumento;

    document.getElementById("fecha_nacimiento").value =
        boton.dataset.fechaNacimiento;

    document.getElementById("contrasena").value = "";

    document.getElementById("rol").value =
        boton.dataset.rol;

    document.getElementById("estado").value =
        boton.dataset.estado;

    document.getElementById("tituloModal").textContent =
        "Editar usuario";

    document.getElementById("textoModal").textContent =
        "Modifique la información del usuario";

    document.getElementById("modalUsuario").classList.add("show");
}

function cerrarModalUsuario()
{
    const modal = document.getElementById("modalUsuario");

    if (modal)
    {
        modal.classList.remove("show");
    }
}

function validarFormularioUsuario(form)
{
    const id = form.querySelector("#id_usuarios");
    const nombres = form.querySelector("#nombres");
    const apellidos = form.querySelector("#apellidos");
    const correo = form.querySelector("#correo");
    const tipoDocumento = form.querySelector("#tipo_documento");
    const numeroDocumento = form.querySelector("#numero_documento");
    const fechaNacimiento = form.querySelector("#fecha_nacimiento");
    const contrasena = form.querySelector("#contrasena");
    const rol = form.querySelector("#rol");
    const estado = form.querySelector("#estado");

    const esNuevo = !id || id.value.trim() === "";

    if (!nombres || nombres.value.trim() === "")
    {
        mostrarAlerta(
            "Validación",
            "Los nombres son obligatorios. Por favor ingrese los nombres."
        );

        if (nombres)
        {
            nombres.focus();
        }

        return false;
    }

    if (nombres.value.trim().length < 2 ||
        nombres.value.trim().length > 50)
    {
        mostrarAlerta(
            "Validación",
            "Los nombres deben tener entre 2 y 50 caracteres."
        );

        nombres.focus();

        return false;
    }

    if (!apellidos || apellidos.value.trim() === "")
    {
        mostrarAlerta(
            "Validación",
            "Los apellidos son obligatorios. Por favor ingrese los apellidos."
        );

        if (apellidos)
        {
            apellidos.focus();
        }

        return false;
    }

    if (apellidos.value.trim().length < 2 ||
        apellidos.value.trim().length > 50)
    {
        mostrarAlerta(
            "Validación",
            "Los apellidos deben tener entre 2 y 50 caracteres."
        );

        apellidos.focus();

        return false;
    }

    if (!correo || correo.value.trim() === "")
    {
        mostrarAlerta(
            "Validación",
            "El correo electrónico es obligatorio."
        );

        if (correo)
        {
            correo.focus();
        }

        return false;
    }

    const expresionCorreo =
        /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!expresionCorreo.test(correo.value.trim()))
    {
        mostrarAlerta(
            "Validación",
            "Ingrese un correo electrónico válido."
        );

        correo.focus();

        return false;
    }

    if (!tipoDocumento || tipoDocumento.value.trim() === "")
    {
        mostrarAlerta(
            "Validación",
            "Debe seleccionar el tipo de documento."
        );

        if (tipoDocumento)
        {
            tipoDocumento.focus();
        }

        return false;
    }

    if (!numeroDocumento ||
        numeroDocumento.value.trim() === "")
    {
        mostrarAlerta(
            "Validación",
            "El número de documento es obligatorio."
        );

        if (numeroDocumento)
        {
            numeroDocumento.focus();
        }

        return false;
    }

    if (numeroDocumento.value.trim().length < 5 ||
        numeroDocumento.value.trim().length > 20)
    {
        mostrarAlerta(
            "Validación",
            "El número de documento debe tener entre 5 y 20 caracteres."
        );

        numeroDocumento.focus();

        return false;
    }

    if (!fechaNacimiento ||
        fechaNacimiento.value.trim() === "")
    {
        mostrarAlerta(
            "Validación",
            "La fecha de nacimiento es obligatoria."
        );

        if (fechaNacimiento)
        {
            fechaNacimiento.focus();
        }

        return false;
    }

    if (esNuevo &&
        (!contrasena || contrasena.value.trim() === ""))
    {
        mostrarAlerta(
            "Validación",
            "La contraseña es obligatoria para crear un usuario."
        );

        if (contrasena)
        {
            contrasena.focus();
        }

        return false;
    }

    if (!rol || rol.value.trim() === "")
    {
        mostrarAlerta(
            "Validación",
            "Debe seleccionar o ingresar un rol."
        );

        if (rol)
        {
            rol.focus();
        }

        return false;
    }

    if (!estado || estado.value.trim() === "")
    {
        mostrarAlerta(
            "Validación",
            "Debe seleccionar el estado del usuario."
        );

        if (estado)
        {
            estado.focus();
        }

        return false;
    }

    return true;
}

function confirmarCambioEstado(boton)
{
    const estado = boton.dataset.estado;
    const formulario = boton.closest("form");

    if (!formulario)
    {
        return false;
    }

    if (estado === "Activo")
    {
        mostrarConfirmacion(
            "¿Desactivar usuario?",
            "¿Está seguro de que desea desactivar este usuario?",
            "Desactivar",
            function()
            {
                formulario.submit();
            }
        );
    }
    else
    {
        mostrarConfirmacion(
            "¿Activar usuario?",
            "¿Está seguro de que desea activar este usuario?",
            "Activar",
            function()
            {
                formulario.submit();
            }
        );
    }

    return false;
}

window.addEventListener("DOMContentLoaded", function()
{
    const formulariosUsuario =
        document.querySelectorAll(
            'form[action$="/view/usuarios/save"]'
        );

    formulariosUsuario.forEach(function(form)
    {
        form.setAttribute("novalidate", "novalidate");

        form.addEventListener("submit", function(event)
        {
            if (!validarFormularioUsuario(this))
            {
                event.preventDefault();
            }
        });
    });

    const modalUsuario =
        document.getElementById("modalUsuario");

    if (modalUsuario)
    {
        window.addEventListener("click", function(event)
        {
            if (event.target === modalUsuario)
            {
                cerrarModalUsuario();
            }
        });
    }
    const mensajeServidor =
        document.body.dataset.message;

    if (mensajeServidor) {
        mostrarAlerta(
            "Operación exitosa",
            mensajeServidor,
            "success"
        );
    }

    const formLogin =
        document.getElementById("formLogin");

    if (formLogin)
    {
        const mensajeError = formLogin.dataset.error;

        if (mensajeError)
        {
            mostrarAlerta(
                "Inicio de sesión",
                mensajeError
            );
        }

        formLogin.addEventListener(
            "submit",
            function(event)
            {
                const usuario =
                    document.getElementById("usuario");

                const contrasena =
                    document.getElementById("contrasena");


                if (!usuario || usuario.value.trim() === "")
                {
                    event.preventDefault();

                    mostrarAlerta(
                        "Inicio de sesión",
                        "Ingrese su usuario."
                    );

                    if (usuario)
                    {
                        usuario.focus();
                    }

                    return;
                }

                if (!contrasena || contrasena.value.trim() === "")
                {
                    event.preventDefault();

                    mostrarAlerta(
                        "Inicio de sesión",
                        "Ingrese su contraseña."
                    );

                    if (contrasena)
                    {
                        contrasena.focus();
                    }
                }
            }
        );
    }
});