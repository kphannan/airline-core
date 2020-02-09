package com.airline.core.order;

import com.airline.core.currency.Price;

import lombok.Data;

@Data
public class Item
{
    private String id;

    private Price  price;


    public Item()
    {}

}