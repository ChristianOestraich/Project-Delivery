package com.app.projectdelivery.controllers;

import com.app.projectdelivery.model.ItemModel;
import com.app.projectdelivery.model.UserModel;
import com.app.projectdelivery.repository.ItemRepository;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ItemModel>> listAll()
    {
        return ResponseEntity.ok( itemRepository.findAll() );
    }

    @PostMapping( "/save" )
    public ResponseEntity<ItemModel> save( @RequestBody @Valid ItemModel item )
    {
        return ResponseEntity.ok( itemRepository.save( item ) );
    }

    @DeleteMapping( value = "/delete/{id}" )
    public ResponseEntity<UserModel> deletePost( @PathVariable Integer itemId )
    {
        itemRepository.delete( itemRepository.findById( itemId ).get() );

        if ( itemId != null )
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }

        return new ResponseEntity<>( HttpStatus.OK );

    }
}
