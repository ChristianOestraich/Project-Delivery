package com.app.projectdelivery.repository;

import com.app.projectdelivery.model.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RequestModel, Integer>
{
    public Optional<RequestModel> findByRequestName( String requestName );
}
