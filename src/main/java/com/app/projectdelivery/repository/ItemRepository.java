package com.app.projectdelivery.repository;

import com.app.projectdelivery.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemModel, Long>
{
    public Optional<ItemModel> findByName( String name );
}
