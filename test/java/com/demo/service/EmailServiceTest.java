package com.demo.service;

import com.demo.domain.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @InjectMocks
    EmailService emailService;

    private Order order;

    @Before
    public void setUp() {
        order = new Order();
    }

    @Test
    public void testSingleton() {
        EmailService instance1 = EmailService.getInstance();
        EmailService instance2 = EmailService.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testSendmailThrowsException() {
        try {
            emailService.sendEmail(order);
            fail("Expected RuntimeException to be thrown");
        } catch (RuntimeException e) {
            assertEquals("An Exception Occurred", e.getMessage());
        }
    }

    @Test
    public void testSendEmailSetsCustomerNotifiedFalse() {
        emailService.sendEmail(order);

        assertFalse(order.isCustomerNotified());  // Ensure customer is not notified
    }

    @Test
    public void testSendEmailSetsCustomerNotifiedTrue() {
        emailService.sendEmail(order, "cc@example.com");

        assertTrue(order.isCustomerNotified());  // Ensure customer is notified
    }
}