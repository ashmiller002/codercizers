package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.UserRepository;
import capstone.workout_buddy.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

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
        User user = new User();
        user.setFirstName("Chad");
        user.setLastName("Ginsy");
        user.setEmail("chad@test.com");
        user.setDateBirth(LocalDate.of(1972, 4, 24));
        user.setLoginId("login111");
        user.setProgramId(2);

        Result<User> result = service.add(user);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(user, result.getPayload());
    }


}