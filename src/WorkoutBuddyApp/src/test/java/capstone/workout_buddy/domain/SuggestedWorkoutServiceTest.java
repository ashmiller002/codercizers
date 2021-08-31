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
    void shouldSuggestRestDayModStrength() {
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
        when(workoutRepository.findById(13)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(5)).thenReturn(categoryList);


        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(13, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestUpperBodyModStrength(){
        User user = makeUser();
        user.setProgramId(1);
        user.setActivityLevelId(1);
        user.setGoalId(1);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(1)).thenReturn(new Program(1, 1, 1));


        Workout mockCatWorkout = makeUpperBodyWorkout();
        List<Workout> categoryList = new ArrayList<>();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockWorkouts = new ArrayList<>();
        Workout workout = makeRestDay();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(workout);
        mockWorkout.setActivityId(1);
        mockWorkouts.add(mockWorkout);
        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockWorkouts);
        when(workoutRepository.findById(1)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(1)).thenReturn(categoryList);


        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(1, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestUpperNullPriorDay(){
        User user = makeUser();
        user.setProgramId(1);
        user.setActivityLevelId(1);
        user.setGoalId(1);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(1)).thenReturn(new Program(1, 1, 1));

        Workout mockCatWorkout = makeUpperBodyWorkout();
        List<Workout> categoryList = new ArrayList<>();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockWorkouts = new ArrayList<>();

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockWorkouts);
        when(workoutRepository.findById(1)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(1)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(1, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestLowerBodyModStrength(){
        User user = makeUser();
        user.setProgramId(1);
        user.setActivityLevelId(1);
        user.setGoalId(1);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(1)).thenReturn(new Program(1, 1, 1));

        Workout mockCatWorkout = makeLowerBodyWorkout();
        List<Workout> categoryList = new ArrayList<>();
        categoryList.add(mockCatWorkout);

        //setting up userWorkout list
        List<UserWorkout> mockWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        Workout workout = makeRestDay();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(workout);
        mockWorkout.setActivityId(1);
        mockWorkouts.add(mockWorkout);
        UserWorkout mockWorkout2 = new UserWorkout();
        workout = makeUpperBodyWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(2));
        mockWorkout2.setWorkout(workout);
        mockWorkout2.setActivityId(3);
        mockWorkouts.add(mockWorkout2);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockWorkouts);
        when(workoutRepository.findById(3)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(2)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(3, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldRecommendUpperBodyHighStrength(){
        User user = makeUser();
        user.setProgramId(2);
        user.setActivityLevelId(2);
        user.setGoalId(1);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(2)).thenReturn(new Program(1, 1, 2));

        Workout mockCatWorkout = makeUpperBodyWorkout();
        List<Workout> categoryList = new ArrayList<>();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockWorkouts = new ArrayList<>();
        Workout workout = makeRestDay();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(workout);
        mockWorkout.setActivityId(1);
        mockWorkouts.add(mockWorkout);
        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockWorkouts);
        when(workoutRepository.findById(1)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(1)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(1, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestLowerBodyHighStrength(){
        User user = makeUser();
        user.setProgramId(2);
        user.setActivityLevelId(2);
        user.setGoalId(1);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(2)).thenReturn(new Program(1, 1, 2));

        Workout mockCatWorkout = makeLowerBodyWorkout();
        List<Workout> categoryList = new ArrayList<>();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeUpperBodyWorkout());
        mockWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeUpperBodyWorkout());
        mockWorkouts.add(mockWorkout2);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockWorkouts);
        when(workoutRepository.findById(3)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(2)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(3, result.getPayload().getWorkoutId());
    }



    User makeUser(){
        User user = new User();
        user.setUserId(1);
        user.setFirstName("Chad");
        user.setLastName("Ginsy");
        user.setEmail("chad@test.com");
        user.setDateBirth(LocalDate.of(1972, 4, 24));
        user.setLoginId("login111");
        user.setProgramId(2);
        user.setGoalId(2);
        user.setActivityLevelId(1);
        return user;
    }

    Workout makeUpperBodyWorkout(){
        Workout workout = new Workout();
        workout.setWorkoutId(1);
        workout.setCategoryId(1);
        workout.setWorkoutName("Upper Body Strength");
        return workout;
    }

    Workout makeLowerBodyWorkout(){
        Workout workout = new Workout();
        workout.setWorkoutId(3);
        workout.setCategoryId(2);
        workout.setWorkoutName("Lower Body");
        return workout;
    }

    Workout makeRestDay(){
        Workout workout = new Workout();
        workout.setWorkoutId(13);
        workout.setCategoryId(5);
        workout.setWorkoutName("Rest Day");
        return workout;
    }

    Workout makeCardio(){
        Workout workout = new Workout();
        workout.setWorkoutId(8);
        workout.setCategoryId(3);
        workout.setWorkoutName("Dance Cardio");
        return workout;
    }

    Workout makeMobilityWorkout(){
        Workout workout = new Workout();
        workout.setWorkoutId(11);
        workout.setCategoryId(4);
        workout.setWorkoutName("Yoga");
        return workout;
    }


}