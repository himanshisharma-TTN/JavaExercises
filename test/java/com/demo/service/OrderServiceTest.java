package com.demo.service;

import com.demo.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    Order order = new Order();

    @Test
    public void testConstructor() {
        assertNotNull(order);
        assertEquals(0, order.getQuantity());
        assertNull(order.getItemName());
        assertEquals(0.0, order.getPrice(), 0.001);  // Use delta for floating-point comparison
        assertEquals(0.0, order.getPriceWithTax(), 0.001);
        assertFalse(order.isCustomerNotified());
    }

    @Test
    public void testSetPrice() {
        order.setPrice(500.0);
        assertEquals(500.0, order.getPrice(), 0.001); // Again, use delta for floating-point comparison
    }

    @Test
    public void testSetCustomerNotified() {
        order.setCustomerNotified(true);
        assertTrue(order.isCustomerNotified());
        order.setCustomerNotified(false);
        assertFalse(order.isCustomerNotified());
    }
}
