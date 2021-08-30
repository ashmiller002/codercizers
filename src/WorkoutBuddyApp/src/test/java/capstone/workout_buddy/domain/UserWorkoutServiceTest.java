package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.UserWorkoutRepository;
import capstone.workout_buddy.models.UserWorkout;
import capstone.workout_buddy.models.Workout;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserWorkoutServiceTest {

    @Autowired
    UserWorkoutService service;

    @MockBean
    UserWorkoutRepository repository;

    @Test
    void shouldAddValidUserWorkout() {
        Workout workout = new Workout(1,"upperBodyTest", 0,  "enable" );
        UserWorkout userWorkout = new UserWorkout();

        userWorkout.setWorkout(workout);
        userWorkout.setUserWorkoutId(0);
        userWorkout.setUserId(1);
        userWorkout.setActivityId(1);
        userWorkout.setWorkoutDate(LocalDate.of(2021,8,2));

        UserWorkout mockOut = new UserWorkout();
        Workout workout2 = new Workout(2,"upperBodyTest", 2,  "enable" );
        mockOut.setWorkout(workout2);
        mockOut.setUserWorkoutId(1);
        mockOut.setUserId(1);
        mockOut.setActivityId(1);
        mockOut.setWorkoutDate(LocalDate.of(2021,8,2));

        when(repository.add(userWorkout)).thenReturn(mockOut);

        Result<UserWorkout> actual = service.add(userWorkout);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldFindWorkout(){
        UserWorkout userWorkout = new UserWorkout();

        Workout workout = new Workout();
        workout.setWorkoutId(2);

        userWorkout.setWorkout(workout);
        userWorkout.setUserWorkoutId(0);
        userWorkout.setUserId(1);
        userWorkout.setActivityId(1);
        userWorkout.setWorkoutDate(LocalDate.of(2021,8,2));
    }

    @Test
    void shouldNotAddInvalidUserWorkout() {
        Workout workout = new Workout(1,"upperBodyTest", 0,  "enable" );
        UserWorkout userWorkout = new UserWorkout();

        userWorkout.setWorkout(workout);
        userWorkout.setUserWorkoutId(0);
        userWorkout.setUserId(1);
        userWorkout.setActivityId(1);
        userWorkout.setWorkoutDate(null);


        Result<UserWorkout> actual = service.add(userWorkout);
        assertEquals(ResultType.INVALID, actual.getType());

    }

//    @Test
//    void shouldFindExistingWorkoutsByUserId() {
//
//        Workout workout = new Workout();
//        Workout workout2 = new Workout();
//        Workout workout3 = new Workout();
//        List<UserWorkout> userWorkouts = Arrays.asList(workout,workout2,workout3);
//        UserWorkout userWorkout = new UserWorkout();
//        userWorkout.setWorkouts(userWorkouts);
//
//        userWorkout.setUserWorkoutId(1);
//        userWorkout.setUserId(3);
//        userWorkout.setActivityId(1);
//        userWorkout.setWorkoutDate(Date.valueOf("2021-8-02"));
//
//        when(repository.findWorkoutsByUserId(3)).thenReturn(userWorkouts);
//
//        List<UserWorkout> actual = service.findWorkoutsByUserId(3);
//        assertEquals(3, actual.size());
//
//    }

}