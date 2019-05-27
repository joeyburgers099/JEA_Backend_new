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
public class DiscountCodeControllerTest {

    DiscountCodeController discountCodeController;
    DiscountCodeRepo discountCodeRepo;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        this.discountCodeRepo = new DiscountCodeRepo();
        this.discountCodeController = new DiscountCodeController();
        this.discountCodeController = mock(DiscountCodeController.class);
    }

    @Test
    public void shouldGetUserById() {
        DiscountCode expected = new DiscountCode();
        when(this.discountCodeController.getById((long) 1))
                .thenReturn(expected);
        DiscountCode actual = this.discountCodeController.getById(ID);
        assertThat(actual, Is.is(equalTo(expected)));
    }

//    @Test
//    public void shouldRemoveUser() {
//        DiscountCode discountCode = new DiscountCode();
//        doNothing().when(this.discountCodeController).delete(Matchers.anyObject());
//        this.discountCodeController.delete( (long) 4 );
//        verify(this.discountCodeController).delete( (long) 5 );
//    }

    @Test
    public void shouldSaveUser() {
        DiscountCode discountCode = new DiscountCode();
        doNothing().when(this.discountCodeController).create(Matchers.anyObject());
        this.discountCodeController.create(discountCode);
        verify(this.discountCodeController).create(discountCode);
    }
}