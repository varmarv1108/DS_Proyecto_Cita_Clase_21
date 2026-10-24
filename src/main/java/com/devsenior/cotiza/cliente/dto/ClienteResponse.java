package com.devsenior.cotiza.cliente.dto;

import java.time.LocalDateTime;

import com.devsenior.cotiza.cliente.Cliente;

public record ClienteResponse(
        Long id,
        String nombre,
        String telefono,
        String correo,
        LocalDateTime fechaCreacion
) {
    public static ClienteResponse desdeEntidad(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNombre(),
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getFechaCreacion()
        );
    }
}
