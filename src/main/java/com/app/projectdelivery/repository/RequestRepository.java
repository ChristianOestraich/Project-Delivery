package com.app.projectdelivery.repository;

import com.app.projectdelivery.model.DeliveryModel;
import com.app.projectdelivery.model.RequestModel;
import com.app.projectdelivery.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<RequestModel, Long>
{
    public Optional<RequestModel> findByUserModelAndDeliveryModel( UserModel user, DeliveryModel delivery );

    @Modifying
    @Query( value = "insert into requests_items (quantity, item_id, request_id) values (:quantity, :item_id, :request_id)",
            nativeQuery = true )
    public void insertUser( @Param( "quantity" ) Integer quantity, @Param( "item_id" ) Long itemId,
                            @Param( "request_id" ) Long requestId );

    @Query( value = "SELECT * FROM requests_items WHERE item_id = :item_id AND request_id = :request_id ", nativeQuery = true )
    List<Object> findItemsForRequest( @Param( "item_id" ) Long itemId,
                                      @Param( "request_id" ) Long requestId );

    @Query( value = "DELETE FROM requests_items WHERE item_id = :item_id AND request_id = :request_id ", nativeQuery = true )
    List<Object> deleteItemsForRequest( @Param( "item_id" ) Long itemId,
                                        @Param( "request_id" ) Long requestId );

}
