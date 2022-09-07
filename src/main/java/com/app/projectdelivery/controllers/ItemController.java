package com.app.projectdelivery.controllers;

import com.app.projectdelivery.model.ItemModel;
import com.app.projectdelivery.repository.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping( "/api/item" )
public class ItemController
{
    private final ItemRepository itemRepository;

    public ItemController( ItemRepository itemRepository )
    {
        this.itemRepository = itemRepository;
    }

    @GetMapping( "/listAll" )
    public List<ItemModel> findAll()
    {
        return itemRepository.findAll();
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<ItemModel> findOne( @PathVariable long id )
    {
        return itemRepository.findById( id )
                             .map( ResponseEntity::ok )
                             .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping( "/save" )
    public ResponseEntity<ItemModel> save( @RequestBody @Valid ItemModel item )
    {
        return saveItem( item );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<?> remove( @PathVariable Long id )
    {
        return itemRepository.findById( id )
                             .map( userModel ->
                             {
                                 itemRepository.deleteById( id );
                                 return ResponseEntity.ok().build();
                             } )
                             .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping( "/edit" )
    public ResponseEntity<ItemModel> editPost( @RequestBody @Valid ItemModel item )
    {
        return saveItem( item );
    }

    private ResponseEntity<ItemModel> saveItem( ItemModel item )
    {
        return ResponseEntity.ok( itemRepository.save( item ) );
    }
}
