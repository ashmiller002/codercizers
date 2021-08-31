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
    void shouldFindWorkoutById(){

        Workout actual = repository.findById(1);
        assertEquals("Upper Body 1", actual.getWorkoutName());

        actual = repository.findById(4);
        assertEquals("Lower Body 1", actual.getWorkoutName());
    }

    @Test
    void shouldNotFindWorkoutByIdMissing(){
        Workout actual = repository.findById(3000);
        assertNull(actual);
    }

    @Test
    void shouldFindAllWorkouts(){
        List<Workout> all = repository.findAll();
        assertNotNull(all);
        assertTrue(all.size() >= 1);
        Workout actual = all.get(0);

        assertEquals(1, actual.getWorkoutId());
        assertEquals("Upper Body 1", actual.getWorkoutName());
    }
    @Test
    void shouldFindWorkoutsByCategory() {
        List<Workout> workouts = repository.findByCategory(1);
        assertNotNull(workouts);
        assertEquals(1, workouts.get(0).getCategoryId());
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
        Workout workout = new Workout(1,"Upper Body 1", 1,  "disable" );
        assertTrue(repository.update(workout));

    }

    @Test
    void shouldNotUpdateMissing(){
        Workout workout = new Workout(70,"upperBodyTest", 70,  "enable" );
        assertFalse(repository.update(workout));
    }


}