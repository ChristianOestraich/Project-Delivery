package com.app.projectdelivery.model;

import com.app.projectdelivery.jpa.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity( name = "items" )
public class ItemModel extends BaseModel
{
    private String name;
    private String description;
    private Double value;

    @ManyToMany( mappedBy = "items" )
    private List<RequestModel> requestModals;

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public Double getValue()
    {
        return value;
    }

    public void setValue( Double value )
    {
        this.value = value;
    }
}
