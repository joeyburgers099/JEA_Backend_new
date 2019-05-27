package controller;

import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class QuizControllerTest {

    QuizController quizController;
    QuizRepo quizRepo;
    private static final Long ID = 1L;

    @Before
    public void setUp() {
        this.quizRepo = new QuizRepo();
        this.quizController = new QuizController();
        this.quizController = mock(QuizController.class);
    }

    @Test
    public void shouldGetUserById() {
        Quiz expected = new Quiz();
        when(this.quizController.getById((long) 1))
                .thenReturn(expected);
        Quiz actual = this.quizController.getById(ID);
        assertThat(actual, Is.is(equalTo(expected)));
    }

//    @Test
//    public void shouldRemoveUser() {
//        Quiz quiz = new Quiz();
//        doNothing().when(this.quizController).delete(Matchers.anyObject());
//        this.quizController.delete(quiz);
//        verify(this.quizController).delete(quiz);
//    }

    @Test
    public void shouldSaveUser() {
        Quiz quiz = new Quiz();
        doNothing().when(this.quizController).create(Matchers.anyObject());
        this.quizController.create(quiz);
        verify(this.quizController).create(quiz);
    }
}