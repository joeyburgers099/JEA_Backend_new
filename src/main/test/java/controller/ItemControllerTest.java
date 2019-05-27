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
public class ItemControllerTest {

    ItemController itemController;
    ItemRepo itemRepo;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        this.itemRepo = new ItemRepo();
        this.itemController = new ItemController();
        this.itemController = mock(ItemController.class);
    }

//    @Test
//    public void shouldGetUserById() {
//        Item expected = new Item();
//        when(this.itemController.getById((long) 1))
//                .thenReturn(expected);
//        Item actual = this.itemController.getById(ID);
//        assertThat(actual, Is.is(equalTo(expected)));
//    }

//    @Test
//    public void shouldRemoveUser() {
//        Item item = new Item();
//        doNothing().when(this.itemController).delete(Matchers.anyObject());
//        this.itemController.delete(item);
//        verify(this.itemController).delete(item);
//    }

    @Test
    public void shouldSaveUser() {
        Item item = new Item();
        doNothing().when(this.itemController).create(Matchers.anyObject());
        this.itemController.create(item);
        verify(this.itemController).create(item);
    }
}
