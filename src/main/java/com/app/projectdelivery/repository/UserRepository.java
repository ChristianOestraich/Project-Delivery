package com.app.projectdelivery.repository;

import com.app.projectdelivery.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer>
{
    public Optional<UserModel> findByUserName( String userName );
}
