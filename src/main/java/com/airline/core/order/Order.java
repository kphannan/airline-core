package com.airline.core.order;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;


@Data
public class Order
{
    private String id;

    private List<Item>  orderItems;


    public Order()
    {
        orderItems = new ArrayList<>();
    }

    public void addToOrder( final Item item )
    {
        orderItems.add(item);
    }

}