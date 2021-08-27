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