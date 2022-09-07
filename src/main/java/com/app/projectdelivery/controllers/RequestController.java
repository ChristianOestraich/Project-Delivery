package com.app.projectdelivery.controllers;

import com.app.projectdelivery.model.RequestModel;
import com.app.projectdelivery.repository.RequestRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( "/api/request" )
public class RequestController
{
    private final RequestRepository requestRepository;

    public RequestController( RequestRepository requestRepository )
    {
        this.requestRepository = requestRepository;
    }

    @GetMapping( "/listAll" )
    public List<RequestModel> findAll()
    {
        return requestRepository.findAll();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<RequestModel> findOne( @PathVariable long id )
    {
        return requestRepository.findById( id )
                                .map( ResponseEntity::ok )
                                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping( "/save" )
    public ResponseEntity<RequestModel> save( @RequestBody @Valid RequestModel request )
    {
        return saveRequest( request );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> remove( @PathVariable Long id )
    {
        return requestRepository.findById( id )
                                .map( userModel ->
                                {
                                    requestRepository.deleteById( id );
                                    return ResponseEntity.ok().build();
                                } )
                                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping( "/edit" )
    public ResponseEntity<RequestModel> editPost( @RequestBody @Valid RequestModel request )
    {
        return saveRequest( request );
    }

    private ResponseEntity<RequestModel> saveRequest( RequestModel request )
    {
        return ResponseEntity.ok( requestRepository.save( request ) );
    }
}
