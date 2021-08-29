package capstone.workout_buddy.data;

import capstone.workout_buddy.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {
    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void shouldFindFantasiaByUserId() {
        User actual = repository.findByUserId(5);
        assertEquals(5, actual.getUserId());
        assertEquals("Fantasia", actual.getFirstName());
    }

    @Test
    void shouldNotFindUserId() {
        User actual = repository.findByUserId(100);
        assertNull(actual);
    }

    @Test
    void shouldFindByLoginId() {
        User actual = repository.findByLoginId("login4");
        assertEquals(4, actual.getUserId());
        assertEquals("Mollusk", actual.getLastName());
    }

    @Test
    void shouldAdd() {
        User user = new User();
        user.setFirstName("Chad");
        user.setLastName("Ginsy");
        user.setEmail("chad@test.com");
        user.setDateBirth(LocalDate.of(1972, 4, 24));
        user.setLoginId("login111");
        user.setProgramId(2);

        User actual = repository.add(user);
        assertNotNull(actual);
        assertEquals(7, actual.getUserId());
    }

    @Test
    void shouldFindAll() {
        List<User> actual = repository.findAll();
        assertNotNull(actual);
        assertEquals(7, actual.size());

    }

    @Test
    void shouldUpdateFirstName() {
        User actual = repository.findByUserId(6);
        actual.setFirstName("Chad");
        boolean result = repository.update(actual);
        assertTrue(result);
        actual = repository.findByUserId(6);
        assertEquals("Chad", actual.getFirstName());
    }

    @Test
    void shouldUpdateLastName(){
        User actual = repository.findByUserId(6);
        actual.setLastName("Ginsy");
        boolean result = repository.update(actual);
        assertTrue(result);
        actual = repository.findByUserId(6);
        assertEquals("Ginsy", actual.getLastName());
    }

    @Test
    void shouldUpdateBirthDate(){
        User actual = repository.findByUserId(6);
        actual.setDateBirth(LocalDate.of(1988, 2, 14));
        boolean result = repository.update(actual);
        assertTrue(result);
        actual = repository.findByUserId(6);
        assertEquals(LocalDate.of(1988, 2, 14), actual.getDateBirth());
    }

    @Test
    void shouldNotUpdateInvalidUser(){
        User actual = repository.findByUserId(6);
        actual.setUserId(100);
        boolean result = repository.update(actual);
        assertFalse(result);
    }

}