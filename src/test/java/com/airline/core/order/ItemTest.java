package com.airline.core.order;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;


public class ItemTest
{
    @Test
    public void noArgsConstructor()
    {
        Item item = new Item();

        assertNotNull( item, "Ensure a default Item is constructed" );
    }

}
