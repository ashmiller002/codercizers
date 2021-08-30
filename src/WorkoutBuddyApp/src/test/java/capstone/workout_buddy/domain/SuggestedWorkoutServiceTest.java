package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.ProgramRepository;
import capstone.workout_buddy.data.UserRepository;
import capstone.workout_buddy.data.UserWorkoutRepository;
import capstone.workout_buddy.data.WorkoutRepository;
import capstone.workout_buddy.models.Program;
import capstone.workout_buddy.models.User;
import capstone.workout_buddy.models.UserWorkout;
import capstone.workout_buddy.models.Workout;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SuggestedWorkoutServiceTest {

    @Autowired
    SuggestedWorkoutService service;

    @MockBean
    UserWorkoutRepository userWorkoutRepository;

    @MockBean
    UserRepository userRepository;

    @MockBean
    ProgramRepository programRepository;

    @MockBean
    WorkoutRepository workoutRepository;


    @Test
    void shouldSuggestRestDay() {
        User user = makeUser();
        user.setUserId(1);
        user.setProgramId(1);
        user.setActivityLevelId(1);
        user.setGoalId(1);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(1)).thenReturn(new Program(1, 1, 1));

        Workout workout = new Workout();
        workout.setWorkoutId(1);
        workout.setCategoryId(1);

        Workout mockCatWorkout = new Workout();
        mockCatWorkout.setWorkoutId(13);
        mockCatWorkout.setCategoryId(5);
        mockCatWorkout.setWorkoutName("Rest Day");
        List<Workout> categoryList = new ArrayList<>();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(workout);
        mockWorkout.setActivityId(13);
        mockWorkouts.add(mockWorkout);
        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockWorkouts);
        when(workoutRepository.findById(13)).thenReturn(workout);
        when(workoutRepository.findByCategory(5)).thenReturn(categoryList);


        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(13, result.getPayload().getWorkoutId());
    }

    User makeUser(){
        User user = new User();
        user.setFirstName("Chad");
        user.setLastName("Ginsy");
        user.setEmail("chad@test.com");
        user.setDateBirth(LocalDate.of(1972, 4, 24));
        user.setLoginId("login111");
//        user.setProgramId(2);
//        user.setGoalId(2);
//        user.setActivityLevelId(1);
        return user;
    }
}