package br.com.fiap.healy.domain.controller.api;

import org.springframework.http.ResponseEntity;

public interface ResourceDTO<Request, Response> {



    ResponseEntity<Response> findById(Long id);

    ResponseEntity<Response> save(Request r);
}
