package com.app.projectdelivery.controllers;

import com.app.projectdelivery.model.DeliveryModel;
import com.app.projectdelivery.repository.DeliveryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( "/api/delivery" )
public class DeliveryController
{
    private final DeliveryRepository deliveryRepository;

    public DeliveryController( DeliveryRepository deliveryRepository )
    {
        this.deliveryRepository = deliveryRepository;
    }

    @GetMapping( "/listAll" )
    public List<DeliveryModel> findAll()
    {
        return deliveryRepository.findAll();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<DeliveryModel> findOne( @PathVariable long id )
    {
        return deliveryRepository.findById( id )
                                 .map( ResponseEntity::ok )
                                 .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping( "/save" )
    public ResponseEntity<DeliveryModel> save( @RequestBody @Valid DeliveryModel delivery )
    {
        return saveDelivery( delivery );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> remove( @PathVariable Long id )
    {
        return deliveryRepository.findById( id )
                                 .map( userModel ->
                                 {
                                     deliveryRepository.deleteById( id );
                                     return ResponseEntity.ok().build();
                                 } )
                                 .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping( "/edit" )
    public ResponseEntity<DeliveryModel> editPost( @RequestBody @Valid DeliveryModel delivery )
    {
        return saveDelivery( delivery );
    }

    private ResponseEntity<DeliveryModel> saveDelivery( DeliveryModel delivery )
    {
        return ResponseEntity.ok( deliveryRepository.save( delivery ) );
    }
}
