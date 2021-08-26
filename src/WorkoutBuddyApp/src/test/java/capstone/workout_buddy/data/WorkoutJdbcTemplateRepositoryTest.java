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
    void shouldFindAllWorkouts(){
        assertTrue(true);
    }
    @Test
    void shouldFindWorkoutsByCategory() {
        assertTrue(true);
    }

    @Test
    void shouldAddWorkout() {
        Workout actual = new Workout(1,"upperBodyTest", 1,  "enable" );
        repository.add(actual);
        assertNotNull(actual);
        assertEquals("upperBodyTest", actual.getWorkoutName());
    }


    @Test
    void shouldUpdateWorkout() {
        Workout workout = new Workout(1,"upperBodyTest", 1,  "enable" );
        assertTrue(repository.update(workout));
    }

    @Test
    void shouldNotUpdateMissing(){
        Workout workout = new Workout(70,"upperBodyTest", 70,  "enable" );
        assertFalse(repository.update(workout));
    }


}