package com.devsenior.cotiza.cliente.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteRequest(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 120, message = "El nombre no puede superar los 120 caracteres")
        String nombre,

        @NotBlank(message = "El teléfono es obligatorio")
        @Pattern(regexp = "^\\d{10}$", message = "El teléfono debe tener exactamente 10 dígitos numéricos")
        String telefono,

        @Email(message = "El correo electrónico debe ser válido")
        @Size(max = 150, message = "El correo electrónico no puede superar los 150 caracteres")
        String correo
) {
}
