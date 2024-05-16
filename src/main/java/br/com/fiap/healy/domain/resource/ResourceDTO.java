package br.com.fiap.healy.domain.resource;

import org.springframework.http.ResponseEntity;

public interface ResourceDTO<Request, Response> {



    ResponseEntity<Response> findById(Long id);

    ResponseEntity<Response> save(Request r);
}
