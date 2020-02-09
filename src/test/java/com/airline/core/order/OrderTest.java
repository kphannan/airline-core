package com.airline.core.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;

// import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


public class OrderTest
{
    private Order order;

    @BeforeEach
    public void noArgsConstructor()
    {
        order = new Order();

        assertNotNull( order );
    }


    @Test
    public void addSingleItemToOrder()
    {
        Item item = new Item();

        order.addToOrder(item);

        assertEquals( item, order.getOrderItems().get(0) );
    }

}
