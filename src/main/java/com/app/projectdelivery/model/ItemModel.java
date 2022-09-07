package com.app.projectdelivery.model;

import com.app.projectdelivery.jpa.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity( name = "items" )
public class ItemModel extends BaseModel
{
    @Column(name = "name_item")
    private String name;
    private String description;
    @Column(name = "value_item")
    private Double value;

    @ManyToMany( mappedBy = "itemModals" )
    @JsonIgnore
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
