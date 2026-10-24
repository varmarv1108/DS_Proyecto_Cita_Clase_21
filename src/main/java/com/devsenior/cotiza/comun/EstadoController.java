package com.devsenior.cotiza.comun;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    @GetMapping
    public Map<String, String> estado() {
        return Map.of(
                "servicio", "cotiza",
                "version", "0.1.0",
                "estado", "OK");
    }
}
