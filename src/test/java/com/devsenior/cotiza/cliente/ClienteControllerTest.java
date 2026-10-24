package com.devsenior.cotiza.cliente;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class ClienteControllerTest {

        @Autowired
        private WebApplicationContext webApplicationContext;

        @Autowired
        private ClienteRepository clienteRepository;

        private MockMvc mockMvc;

        @BeforeEach
        void setUp() {
                this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
                clienteRepository.deleteAll();
        }

        @Test
        @DisplayName("POST /api/clientes con datos válidos debe retornar 201")
        void crearClienteValidoRetorna201() throws Exception {
                String jsonPeticion = """
                                {
                                    "nombre": "Patricia Roble",
                                    "telefono": "3001234567",
                                    "correo": "patricia@robleypino.com"
                                }
                                """;

                mockMvc.perform(post("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPeticion))
                                .andExpect(status().isCreated())
                                .andExpect(jsonPath("$.id", notNullValue()))
                                .andExpect(jsonPath("$.nombre", is("Patricia Roble")))
                                .andExpect(jsonPath("$.telefono", is("3001234567")))
                                .andExpect(jsonPath("$.correo", is("patricia@robleypino.com")))
                                .andExpect(jsonPath("$.fechaCreacion", notNullValue()));
        }

        @Test
        @DisplayName("POST /api/clientes con teléfono duplicado debe retornar 409")
        void crearClienteConTelefonoDuplicadoRetorna409() throws Exception {
                String jsonOriginal = """
                                {
                                    "nombre": "Cliente Uno",
                                    "telefono": "3119876543",
                                    "correo": "uno@correo.com"
                                }
                                """;

                mockMvc.perform(post("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonOriginal))
                                .andExpect(status().isCreated());

                String jsonDuplicado = """
                                {
                                    "nombre": "Cliente Dos",
                                    "telefono": "3119876543",
                                    "correo": "dos@correo.com"
                                }
                                """;

                mockMvc.perform(post("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonDuplicado))
                                .andExpect(status().isConflict());
        }

        @Test
        @DisplayName("POST /api/clientes con datos inválidos debe retornar 400")
        void crearClienteInvalidoRetorna400() throws Exception {
                // Nombre vacío, teléfono menor a 10 dígitos, correo inválido
                String jsonInvalido = """
                                {
                                    "nombre": "   ",
                                    "telefono": "12345",
                                    "correo": "no-es-correo"
                                }
                                """;

                mockMvc.perform(post("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonInvalido))
                                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("GET /api/clientes debe retornar lista con código 200")
        void obtenerTodosLosClientesRetorna200() throws Exception {
                String jsonPeticion = """
                                {
                                    "nombre": "Carlos Gómez",
                                    "telefono": "3158889900",
                                    "correo": "carlos@correo.com"
                                }
                                """;

                mockMvc.perform(post("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPeticion))
                                .andExpect(status().isCreated());

                mockMvc.perform(get("/api/clientes"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", hasSize(1)))
                                .andExpect(jsonPath("$[0].nombre", is("Carlos Gómez")));
        }

        @Test
        @DisplayName("GET /api/clientes/{id} debe retornar 200 si existe")
        void obtenerPorIdExistenteRetorna200() throws Exception {
                String jsonPeticion = """
                                {
                                    "nombre": "Ana Pérez",
                                    "telefono": "3105556677",
                                    "correo": null
                                }
                                """;

                String respuestaJson = mockMvc.perform(post("/api/clientes")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonPeticion))
                                .andExpect(status().isCreated())
                                .andReturn()
                                .getResponse()
                                .getContentAsString();

                // Extraer id creado para buscarlo
                Long idGenerado = clienteRepository.findAll().get(0).getId();

                mockMvc.perform(get("/api/clientes/" + idGenerado))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id", is(idGenerado.intValue())))
                                .andExpect(jsonPath("$.nombre", is("Ana Pérez")))
                                .andExpect(jsonPath("$.telefono", is("3105556677")));
        }

        @Test
        @DisplayName("GET /api/clientes/{id} debe retornar 404 si no existe")
        void obtenerPorIdInexistenteRetorna404() throws Exception {
                mockMvc.perform(get("/api/clientes/999999"))
                                .andExpect(status().isNotFound());
        }
}
