package com.app.projectdelivery.controllers;

import com.app.projectdelivery.model.UserModel;
import com.app.projectdelivery.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping( "/api/user" )
public class UserController
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController( UserRepository userRepository, PasswordEncoder passwordEncoder )
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping( "/listAll" )
    public ResponseEntity<List<UserModel>> listAll()
    {
        return ResponseEntity.ok( userRepository.findAll() );
    }

    @PostMapping( "/save" )
    public ResponseEntity<UserModel> save( @RequestBody @Valid UserModel user )
    {
        user.setPassword( passwordEncoder.encode( user.getPassword() ) );
        return ResponseEntity.ok( userRepository.save( user ) );
    }

    @DeleteMapping( value = "/delete/{id}" )
    public ResponseEntity<UserModel> deletePost( @PathVariable Integer userId )
    {
        userRepository.delete( userRepository.findById( userId ).get() );

        if ( userId != null )
        {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }

        return new ResponseEntity<>( HttpStatus.OK );

    }

    @PostMapping( "/edit/{id}" )
    public ResponseEntity<UserModel> editPost( @PathVariable UserModel userId )
    {
        return null;
    }

    @GetMapping( "/validatePassword" )
    public ResponseEntity<Boolean> validatePassword( @RequestParam String userName,
                                                     @RequestParam String password )
    {
        Optional<UserModel> optUser = userRepository.findByUserName( userName );
        if ( optUser.toString().isEmpty() )
        {
            return ResponseEntity.status( HttpStatus.UNAUTHORIZED ).body( false );
        }

        UserModel user = optUser.get();
        boolean valid = passwordEncoder.matches( password, user.getPassword() );

        HttpStatus status = ( valid ) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status( status ).body( valid );
    }
}
