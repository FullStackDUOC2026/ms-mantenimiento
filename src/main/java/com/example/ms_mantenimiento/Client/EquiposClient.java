package com.example.ms_mantenimiento.Client;

import com.example.ms_mantenimiento.DTO.EquiposDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class EquiposClient {

    private final WebClient webClient;

    public EquiposClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public EquiposDTO getEquipoById(Long id) {

        return webClient.get()
                .uri("http://localhost:8081/equipos/" + id)
                .retrieve()
                .bodyToMono(EquiposDTO.class)
                .block();
    }

    public EquiposDTO marcarMantencion(Long id) {

        return webClient.put()
                .uri("http://localhost:8081/equipos/" + id + "/mantencion")
                .retrieve()
                .bodyToMono(EquiposDTO.class)
                .block();
    }
}
