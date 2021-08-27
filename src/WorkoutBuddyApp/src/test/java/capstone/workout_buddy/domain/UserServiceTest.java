package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.UserRepository;
import capstone.workout_buddy.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;

    @Test
    void shouldFindFantasiaByUserId() {
    }

    @Test
    void findByLoginId() {
    }

    @Test
    void shouldAddNewUser() {
        User user = makeUser();
        User mock = makeUser();
        mock.setUserId(7);

        when(repository.add(user)).thenReturn(mock);

        Result<User> result = service.add(user);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(mock, result.getPayload());
    }

    @Test
    void shouldAddUpdateProgramId(){
        User user = makeUser();
        user.setGoalId(1);
        user.setActivityLevelId(1);
        User mock = makeUser();
        mock.setUserId(7);
        mock.setProgramId(1);

        when(repository.add(user)).thenReturn(mock);
        Result<User> result = service.add(user);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(mock, result.getPayload());


    }

    @Test
    void shouldNotAddNullUser() {
        User user = null;

        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddDuplicateEmail(){
        User user = makeUser();
        ArrayList<User> list = new ArrayList<>();
        list.add(user);
        when(repository.findAll()).thenReturn(list);

        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("This email is already registered to an account", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddNullBlankEmail(){
        User user = makeUser();
        user.setEmail("");
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("Cannot add user without valid email.", result.getMessages().get(0));

        user.setEmail(null);
        result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("Cannot add user without valid email.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddNullBlankFirstName(){
        User user = makeUser();
        user.setFirstName("");
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("First name required.", result.getMessages().get(0));

        user.setFirstName(null);
        result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("First name required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddNullBlankLastName(){
        User user = makeUser();
        user.setLastName("");
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("Last name required.", result.getMessages().get(0));

        user.setLastName(null);
        result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("Last name required.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddInvalidDateBirth(){
        User user = makeUser();
        user.setDateBirth(LocalDate.now());
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("Must provide valid date of birth.", result.getMessages().get(0));

        user.setDateBirth(LocalDate.of(2021, 10, 8));
        result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("Must provide valid date of birth.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddNullDateBirth(){
        User user = makeUser();
        user.setDateBirth(null);
        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
        assertEquals("Date of birth required.", result.getMessages().get(0));
    }



    User makeUser(){
        User user = new User();
        user.setFirstName("Chad");
        user.setLastName("Ginsy");
        user.setEmail("chad@test.com");
        user.setDateBirth(LocalDate.of(1972, 4, 24));
        user.setLoginId("login111");
        user.setProgramId(2);
        return user;
    }


}