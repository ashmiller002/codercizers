package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.WorkoutRepository;
import capstone.workout_buddy.models.Workout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class WorkoutServiceTest {

    @Autowired
    WorkoutService service;

    @MockBean
    WorkoutRepository repository;

    @Test
    void findByCategory() {
    }

    @Test
    void shouldAddWhenValid() {
        Workout workout = new Workout(1, "TEST WORKOUT", 0,"enable");
        Workout mockOut = new Workout(1, "TEST WORKOUT", 1,"enable" );

        when(repository.add(workout)).thenReturn(mockOut);
        Result<Workout> actual = service.add(workout);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());

    }

    @Test
    void shouldNotAddWhenInvalid() {
        Workout workout = new Workout(0, "TEST WORKOUT", 0,"enable");
        Result<Workout> actual = service.add(workout);
        assertEquals(ResultType.INVALID, actual.getType());
    }

    @Test
    void shouldUpdateWhenValid() {
        Workout workout = new Workout(2,"lowerBodyTest", 2,  "enable" );
        when(repository.update(workout)).thenReturn(true);
        Result<Workout> actual = service.update(workout);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

}