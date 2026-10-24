package com.devsenior.cotiza.cliente;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsenior.cotiza.cliente.dto.ClienteRequest;
import com.devsenior.cotiza.cliente.dto.ClienteResponse;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteResponse crear(ClienteRequest peticion) {
        if (clienteRepository.existsByTelefono(peticion.telefono())) {
            throw new TelefonoDuplicadoException(
                    "Ya existe un cliente con el teléfono: " + peticion.telefono());
        }

        String correoNormalizado = (peticion.correo() != null && !peticion.correo().trim().isEmpty())
                ? peticion.correo().trim()
                : null;

        Cliente cliente = new Cliente(
                peticion.nombre().trim(),
                peticion.telefono().trim(),
                correoNormalizado);

        Cliente guardado = clienteRepository.save(cliente);
        return ClienteResponse.desdeEntidad(guardado);
    }

    @Transactional(readOnly = true)
    public List<ClienteResponse> obtenerTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteResponse::desdeEntidad)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponse obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .map(ClienteResponse::desdeEntidad)
                .orElseThrow(() -> new ClienteNoEncontradoException(
                        "Cliente no encontrado con id: " + id));
    }
}
