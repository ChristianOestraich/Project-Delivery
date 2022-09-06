package com.app.projectdelivery.model;

import com.app.projectdelivery.jpa.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity( name = "deliveries" )
public class DeliveryModel extends BaseModel
{
    @OneToOne( cascade = CascadeType.ALL )
    @JoinColumn( name = "request_id" )
    private RequestModel requestModal;
    private String deliveryAddress;

    public RequestModel getRequestModal()
    {
        return requestModal;
    }

    public void setRequestModal( RequestModel requestModal )
    {
        this.requestModal = requestModal;
    }

    public String getDeliveryAddress()
    {
        return deliveryAddress;
    }

    public void setDeliveryAddress( String deliveryAddress )
    {
        this.deliveryAddress = deliveryAddress;
    }
}
