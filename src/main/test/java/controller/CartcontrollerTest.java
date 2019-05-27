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
public class CartcontrollerTest {

    Cartcontroller cartcontroller;
    CartRepo cartRepo;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        this.cartRepo = new CartRepo();
        this.cartcontroller = new Cartcontroller();
        this.cartcontroller = mock(Cartcontroller.class);
    }

    @Test
    public void shouldGetUserById() {
        Cart expected = new Cart();
        when(this.cartcontroller.getById((long) 1))
                .thenReturn(expected);
        Cart actual = this.cartcontroller.getById(ID);
        assertThat(actual, Is.is(equalTo(expected)));
    }

//    @Test
//    public void shouldRemoveUser() {
//        Cart cart = new Cart();
//        doNothing().when(this.cartcontroller).delete(Matchers.anyObject());
//        this.cartcontroller.delete(cart);
//        verify(this.cartcontroller).delete(cart);
//    }

    @Test
    public void shouldSaveUser() {
        Cart cart = new Cart();
        doNothing().when(this.cartcontroller).create(Matchers.anyObject());
        this.cartcontroller.create(cart);
        verify(this.cartcontroller).create(cart);
    }
}