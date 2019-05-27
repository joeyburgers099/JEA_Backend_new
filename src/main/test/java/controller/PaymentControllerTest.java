package controller;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PaymentControllerTest {

    PaymentController paymentController;
    PaymentRepo paymentRepo;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        this.paymentRepo = new PaymentRepo();
        this.paymentController = new PaymentController();
        this.paymentController = mock(PaymentController.class);
    }

    @Test
    public void shouldGetUserById() {
        Payment expected = new Payment();
        when(this.paymentController.getById((long) 1))
                .thenReturn(expected);
        Payment actual = this.paymentController.getById(ID);
        assertThat(actual, Is.is(equalTo(expected)));
    }

//    @Test
//    public void shouldRemoveUser() {
//        Payment payment = new Payment();
//        doNothing().when(this.paymentController).delete(Matchers.anyObject());
//        this.paymentController.delete(payment);
//        verify(this.paymentController).delete(payment);
//    }

    @Test
    public void shouldSaveUser() {
        Payment payment = new Payment();
        doNothing().when(this.paymentController).create(Matchers.anyObject());
        this.paymentController.create(payment);
        verify(this.paymentController).create(payment);
    }
}