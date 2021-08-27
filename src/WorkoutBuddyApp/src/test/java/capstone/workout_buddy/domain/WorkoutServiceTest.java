package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.WorkoutRepository;
import capstone.workout_buddy.models.Workout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WorkoutServiceTest {

    @Autowired
    WorkoutService service;

    @MockBean
    WorkoutRepository repository;

    @Test
    void shouldFindAllWorkouts(){
        when(repository.findAll()).thenReturn(Arrays.asList(
                new Workout(1,"TestWorkout", 1,  "enable" ),
                new Workout(2,"TestWorkout", 2,  "enable" ),
                new Workout(3,"TestWorkout", 3,  "enable" ),
                new Workout(4,"TestWorkout", 4,  "enable" )
        ));

        List<Workout> actual = service.findAll();
        assertEquals(4, actual.size());
        assertEquals(2, actual.get(1).getWorkoutId());
    }

    @Test
    void shouldFindByWorkoutCategory() {

        when(repository.findByCategory(3)).thenReturn(Arrays.asList(
                new Workout(1,"TestWorkout", 1,  "enable" ),
                new Workout(2,"TestWorkout", 2,  "enable" ),
                new Workout(3,"TestWorkout", 3,  "enable" ),
                new Workout(4,"TestWorkout", 4,  "enable" )
        ));

        List<Workout> actual = service.findByCategory(3);
        assertEquals(4, actual.size());
        assertEquals(3, actual.get(2).getCategoryId());
    }

    @Test
    void shouldFindByWorkoutId() {
        Workout actual = new Workout(1,"Test Workout", 12,  "enable" );
        when(repository.findById(12)).thenReturn(actual);
        assertNotNull(actual);
        assertEquals(12, actual.getWorkoutId());
        assertEquals("Test Workout", actual.getWorkoutName());
    }

    @Test
    void shouldAddWorkoutWhenValid() {
        Workout workout = new Workout(1,"upperBodyTest", 0,  "enable" );
        Workout mockOut = new Workout(1,"upperBodyTest", 2,  "enable" );

        when(repository.add(workout)).thenReturn(mockOut);

        Result<Workout> actual = service.add(workout);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        Workout workout = new Workout(1,"upperBodyTest", 33,  "enable" );
        Result<Workout> actual = service.add(workout);
        assertEquals(ResultType.INVALID, actual.getType());

        workout.setWorkoutId(0);
        workout.setWorkoutName(null);
        actual = service.add(workout);
        assertEquals(ResultType.INVALID, actual.getType());

        workout.setWorkoutName(" ");
        actual = service.add(workout);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdateValidWorkout() {
        Workout workout = new Workout(1,"Test Workout", 12,  "enable" );

        when(repository.update(workout)).thenReturn(true);
        Result<Workout> actual = service.update(workout);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotUpdateMissingWorkout() {
        Workout workout = new Workout(1,"Test Workout", 62,  "enable" );

        when(repository.update(workout)).thenReturn(false);
        Result<Workout> actual = service.update(workout);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }

}