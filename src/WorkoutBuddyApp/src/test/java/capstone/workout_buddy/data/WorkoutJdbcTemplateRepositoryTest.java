package capstone.workout_buddy.data;

import capstone.workout_buddy.models.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
    void shouldFindWorkoutsByCategory() {
        List<Workout> workouts = repository.findByCategory(1);
        assertNotNull(workouts);
    }

    @Test
    void shouldAddWorkout() {
        Workout actual = new Workout(1,"upperBodyTest", 1,  "enable" );
        repository.add(actual);
        assertNotNull(actual);
        assertEquals("upperBodyTest", actual.getWorkoutName());
    }


    @Test
    void shouldUpdateExistingWorkout() {
        Workout actual = new Workout(2,"lowerBodyTest", 2,  "enable" );
        assertTrue(repository.update(actual));
    }

    @Test
    void shouldNotUpdateMissingWorkout() {
        Workout actual = new Workout(300,"unrealisticWorkout", 200,  "enable" );
        assertFalse(repository.update(actual));
    }


}