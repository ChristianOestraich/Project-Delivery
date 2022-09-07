package com.app.projectdelivery.repository;

import com.app.projectdelivery.model.DeliveryModel;
import com.app.projectdelivery.model.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryModel, Long>
{
    Optional<DeliveryModel> findByRequestModal( RequestModel request );
}
