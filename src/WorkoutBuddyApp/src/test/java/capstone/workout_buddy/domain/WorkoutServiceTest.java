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
        Workout expected = makeWorkout();
        Workout arg = makeWorkout();
        arg.setWorkoutId(0);

        when(repository.add(arg)).thenReturn(expected);
        Result<Workout> result = service.add(arg);
        assertEquals(ResultType.SUCCESS, result.getType());

        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        Workout workout = makeWorkout();
        Result<Workout> result = service.add(workout);
        assertEquals(ResultType.INVALID, result.getType());
        workout.setWorkoutId(0);
        workout.setWorkoutName(null);
        result = service.add(workout);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void update() {
    }

    Workout makeWorkout() {
        Workout workout = new Workout();
        makeWorkout().setWorkoutId(1);
        makeWorkout().setWorkoutName("TestWorkout1");
        makeWorkout().setCategoryId(1);
        makeWorkout().setImageUrl("http://testpics.org/1");
        return workout;
    }

}