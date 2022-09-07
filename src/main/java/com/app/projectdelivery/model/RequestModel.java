package com.app.projectdelivery.model;

import com.app.projectdelivery.jpa.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity( name = "requests" )
public class RequestModel extends BaseModel
{
    @ManyToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "user_id" )
    private UserModel userModel;
    private Double totalValue;
    @ManyToMany( cascade = CascadeType.ALL )
    @JoinTable( name = "requests_items",
                joinColumns = @JoinColumn( name = "request_id" ),
                inverseJoinColumns = @JoinColumn( name = "item_id" ) )
    @JsonIgnore
    private List<ItemModel> itemModals;

    @OneToOne( mappedBy = "requestModal" )
    private DeliveryModel deliveryModel;

    public DeliveryModel getDeliveryModel()
    {
        return deliveryModel;
    }

    public void setDeliveryModel( DeliveryModel deliveryModel )
    {
        this.deliveryModel = deliveryModel;
    }

    public Double getTotalValue()
    {
        return totalValue;
    }

    public void setTotalValue( Double totalValue )
    {
        this.totalValue = totalValue;
    }

    public List<ItemModel> getItemModals()
    {
        return itemModals;
    }

    public void setItemModals( List<ItemModel> itemModals )
    {
        this.itemModals = itemModals;
    }

    public UserModel getUserModel()
    {
        return userModel;
    }

    public void setUserModel( UserModel userModel )
    {
        this.userModel = userModel;
    }
}
