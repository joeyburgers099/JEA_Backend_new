package controller;

import domain.User;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    UserController userController;
    UserRepo userRepo;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        this.userRepo = new UserRepo();
        this.userController = new UserController();
        this.userController = mock(UserController.class);
    }

    @Test
    public void shouldGetUserById() {
        User expected = new User();
        when(this.userController.getById((long) 1))
                .thenReturn(expected);
        User actual = this.userController.getById(ID);
        assertThat(actual, Is.is(equalTo(expected)));
    }

    @Test
    public void shouldRemoveUser() {
//        User user = new User();
//        doNothing().when(this.userController).delete(Matchers.anyObject());
//        this.userController.delete(user);
//        verify(this.userController).delete(user);
    }

    @Test
    public void shouldSaveUser() {
        User user = new User();
        doNothing().when(this.userController).create(Matchers.anyObject());
        this.userController.create(user);
        verify(this.userController).create(user);
    }
}