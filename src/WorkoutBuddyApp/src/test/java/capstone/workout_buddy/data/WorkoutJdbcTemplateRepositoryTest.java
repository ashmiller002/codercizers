package capstone.workout_buddy.data;

import capstone.workout_buddy.models.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WorkoutJdbcTemplateRepositoryTest {

    @Autowired
    WorkoutJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void findByCategory() {
        assertTrue(true);
    }

    @Test
    void shouldAdd() {
        Workout actual = new Workout(30, "upperBodyTest", 20,  "enable" );
        repository.add(actual);

        assertNotNull(actual);
    }
    //int workoutId, String workoutName, int categoryId, String imageUrl, String workoutStatus)

    @Test
    void update() {
        assertTrue(true);
    }
}