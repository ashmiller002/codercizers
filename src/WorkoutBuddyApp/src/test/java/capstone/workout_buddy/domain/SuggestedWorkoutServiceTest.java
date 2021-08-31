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

    @Test
    void shouldSuggestMobCardioHighStrength(){
        User user = makeUser();
        user.setProgramId(2);
        user.setActivityLevelId(2);
        user.setGoalId(1);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(2)).thenReturn(new Program(1, 1, 2));

        Workout mockCatWorkout = makeMobilityWorkout();
        List<Workout> categoryMob4List = new ArrayList<>();
        categoryMob4List.add(mockCatWorkout);
        Workout mockCatWorkout2 = makeCardio();
        List<Workout> categoryCar3List = new ArrayList<>();
        categoryCar3List.add(mockCatWorkout2);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeUpperBodyWorkout());
        mockUserWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeUpperBodyWorkout());
        mockUserWorkouts.add(mockWorkout2);

        UserWorkout mockWorkout3 = new UserWorkout();
        mockWorkout3.setUserWorkoutId(3);
        mockWorkout3.setWorkout(makeLowerBodyWorkout());
        mockWorkout3.setWorkoutDate(LocalDate.now().minusDays(2));
        mockUserWorkouts.add(mockWorkout3);

        UserWorkout mockWorkout4 = new UserWorkout();
        mockWorkout4.setUserWorkoutId(4);
        mockWorkout4.setWorkout(makeLowerBodyWorkout());
        mockWorkout4.setWorkoutDate(LocalDate.now().minusDays(4));
        mockUserWorkouts.add(mockWorkout4);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(11)).thenReturn(mockCatWorkout);
        when(workoutRepository.findById(8)).thenReturn(mockCatWorkout2);
        when(workoutRepository.findByCategory(3)).thenReturn(categoryCar3List);
        when(workoutRepository.findByCategory(4)).thenReturn(categoryMob4List);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldSuggestRestDayHighStrength(){
        User user = makeUser();
        user.setProgramId(4);
        user.setActivityLevelId(2);
        user.setGoalId(1);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(4)).thenReturn(new Program(4, 1, 2));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeRestDay();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeUpperBodyWorkout());
        mockUserWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeUpperBodyWorkout());
        mockUserWorkouts.add(mockWorkout2);

        UserWorkout mockWorkout3 = new UserWorkout();
        mockWorkout3.setUserWorkoutId(3);
        mockWorkout3.setWorkout(makeLowerBodyWorkout());
        mockWorkout3.setWorkoutDate(LocalDate.now().minusDays(2));
        mockUserWorkouts.add(mockWorkout3);

        UserWorkout mockWorkout4 = new UserWorkout();
        mockWorkout4.setUserWorkoutId(4);
        mockWorkout4.setWorkout(makeLowerBodyWorkout());
        mockWorkout4.setWorkoutDate(LocalDate.now().minusDays(4));
        mockUserWorkouts.add(mockWorkout4);

        UserWorkout mockWorkout5 = new UserWorkout();
        mockWorkout5.setWorkout(makeMobilityWorkout());
        mockWorkout5.setUserWorkoutId(5);
        mockWorkout5.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout5);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(13)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(5)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(13, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestRestModMobility(){
        User user = makeUser();
        user.setProgramId(2);
        user.setActivityLevelId(1);
        user.setGoalId(2);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(2)).thenReturn(new Program(1, 2, 1));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeRestDay();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeMobilityWorkout());
        mockUserWorkouts.add(mockWorkout);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(13)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(5)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(13, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestMobilityModMobilityEmptyHistory(){
        User user = makeUser();
        user.setProgramId(2);
        user.setActivityLevelId(1);
        user.setGoalId(2);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(2)).thenReturn(new Program(1, 2, 1));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeMobilityWorkout();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(11)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(4)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(11, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestMobilityModMobility(){
        User user = makeUser();
        user.setProgramId(2);
        user.setActivityLevelId(1);
        user.setGoalId(2);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(2)).thenReturn(new Program(1, 2, 1));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeMobilityWorkout();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeRestDay());
        mockUserWorkouts.add(mockWorkout);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(11)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(4)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(11, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestRestModMobility3Existing(){
        User user = makeUser();
        user.setProgramId(2);
        user.setActivityLevelId(1);
        user.setGoalId(2);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(2)).thenReturn(new Program(1, 2, 1));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeRestDay();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeMobilityWorkout());
        mockUserWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeMobilityWorkout());
        mockUserWorkouts.add(mockWorkout2);

        UserWorkout mockWorkout3 = new UserWorkout();
        mockWorkout3.setUserWorkoutId(3);
        mockWorkout3.setWorkout(makeMobilityWorkout());
        mockWorkout3.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout3);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(13)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(5)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(13, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestMobilityHighMob(){
        User user = makeUser();
        user.setProgramId(5);
        user.setActivityLevelId(2);
        user.setGoalId(2);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(5)).thenReturn(new Program(5, 2, 2));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeMobilityWorkout();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeMobilityWorkout());
        mockUserWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeMobilityWorkout());
        mockUserWorkouts.add(mockWorkout2);

        UserWorkout mockWorkout3 = new UserWorkout();
        mockWorkout3.setUserWorkoutId(3);
        mockWorkout3.setWorkout(makeMobilityWorkout());
        mockWorkout3.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout3);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(11)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(4)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(11, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestMobilityHighMobEmptyUserWorkout(){
        User user = makeUser();
        user.setProgramId(5);
        user.setActivityLevelId(2);
        user.setGoalId(2);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(5)).thenReturn(new Program(5, 2, 2));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeMobilityWorkout();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(11)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(4)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(11, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestNonMobilityMobHigh(){
        User user = makeUser();
        user.setProgramId(5);
        user.setActivityLevelId(2);
        user.setGoalId(2);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(5)).thenReturn(new Program(5, 2, 2));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeUpperBodyWorkout();
        categoryList.add(mockCatWorkout);

        List<Workout> categoryList2 = new ArrayList<>();
        Workout mockCatWorkout2 = makeLowerBodyWorkout();
        categoryList.add(mockCatWorkout);

        List<Workout> categoryList3 = new ArrayList<>();
        Workout mockCatWorkout3 = makeCardio();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeMobilityWorkout());
        mockUserWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeMobilityWorkout());
        mockUserWorkouts.add(mockWorkout2);

        UserWorkout mockWorkout3 = new UserWorkout();
        mockWorkout3.setUserWorkoutId(3);
        mockWorkout3.setWorkout(makeMobilityWorkout());
        mockWorkout3.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout3);

        UserWorkout mockWorkout4 = new UserWorkout();
        mockWorkout4.setUserWorkoutId(4);
        mockWorkout4.setWorkout(makeMobilityWorkout());
        mockWorkout4.setWorkoutDate(LocalDate.now().minusDays(4));
        mockUserWorkouts.add(mockWorkout4);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(1)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(1)).thenReturn(categoryList);
        when(workoutRepository.findById(3)).thenReturn(mockCatWorkout2);
        when(workoutRepository.findByCategory(2)).thenReturn(categoryList2);
        when(workoutRepository.findById(8)).thenReturn(mockCatWorkout3);
        when(workoutRepository.findByCategory(3)).thenReturn(categoryList3);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertNotEquals(13, result.getPayload().getWorkoutId());
        assertNotEquals(11, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestRestMobHigh(){
        User user = makeUser();
        user.setProgramId(5);
        user.setActivityLevelId(2);
        user.setGoalId(2);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(5)).thenReturn(new Program(5, 2, 2));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeRestDay();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeMobilityWorkout());
        mockUserWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeMobilityWorkout());
        mockUserWorkouts.add(mockWorkout2);

        UserWorkout mockWorkout3 = new UserWorkout();
        mockWorkout3.setUserWorkoutId(3);
        mockWorkout3.setWorkout(makeMobilityWorkout());
        mockWorkout3.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout3);

        UserWorkout mockWorkout4 = new UserWorkout();
        mockWorkout4.setUserWorkoutId(4);
        mockWorkout4.setWorkout(makeMobilityWorkout());
        mockWorkout4.setWorkoutDate(LocalDate.now().minusDays(4));
        mockUserWorkouts.add(mockWorkout4);

        UserWorkout mockWorkout5 = new UserWorkout();
        mockWorkout5.setWorkout(makeLowerBodyWorkout());
        mockWorkout5.setUserWorkoutId(5);
        mockWorkout5.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout5);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(13)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(5)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(13, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestCardioEmptyUserWorkoutModCardio(){
            User user = makeUser();
            user.setProgramId(3);
            user.setActivityLevelId(1);
            user.setGoalId(3);
            when(userRepository.findByUserId(1)).thenReturn(user);
            when(programRepository.findById(3)).thenReturn(new Program(3, 3, 1));

            List<Workout> categoryList = new ArrayList<>();
            Workout mockCatWorkout = makeCardio();
            categoryList.add(mockCatWorkout);

            List<UserWorkout> mockUserWorkouts = new ArrayList<>();
            UserWorkout mockWorkout = new UserWorkout();

            when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
            when(workoutRepository.findById(8)).thenReturn(mockCatWorkout);
            when(workoutRepository.findByCategory(3)).thenReturn(categoryList);

            Result<Workout> result = service.suggestWorkout(1);
            assertNotNull(result.getPayload());
            assertTrue(result.isSuccess());
            assertEquals(8, result.getPayload().getWorkoutId());
        }

    @Test
    void shouldSuggestRestModCardio3Existing(){
        User user = makeUser();
        user.setProgramId(3);
        user.setActivityLevelId(1);
        user.setGoalId(3);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(3)).thenReturn(new Program(3, 3, 1));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeRestDay();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(2));
        mockWorkout.setWorkout(makeCardio());
        mockUserWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeCardio());
        mockUserWorkouts.add(mockWorkout2);

        UserWorkout mockWorkout3 = new UserWorkout();
        mockWorkout3.setUserWorkoutId(3);
        mockWorkout3.setWorkout(makeCardio());
        mockWorkout3.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout3);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(13)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(5)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(13, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestRestCardioHigh(){
        User user = makeUser();
        user.setProgramId(3);
        user.setActivityLevelId(2);
        user.setGoalId(3);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(3)).thenReturn(new Program(3, 3, 2));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeRestDay();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeCardio());
        mockUserWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeCardio());
        mockUserWorkouts.add(mockWorkout2);

        UserWorkout mockWorkout3 = new UserWorkout();
        mockWorkout3.setUserWorkoutId(3);
        mockWorkout3.setWorkout(makeCardio());
        mockWorkout3.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout3);

        UserWorkout mockWorkout4 = new UserWorkout();
        mockWorkout4.setUserWorkoutId(4);
        mockWorkout4.setWorkout(makeCardio());
        mockWorkout4.setWorkoutDate(LocalDate.now().minusDays(4));
        mockUserWorkouts.add(mockWorkout4);

        UserWorkout mockWorkout5 = new UserWorkout();
        mockWorkout5.setWorkout(makeLowerBodyWorkout());
        mockWorkout5.setUserWorkoutId(5);
        mockWorkout5.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout5);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(13)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(5)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(13, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestNonCardioCardioHigh(){
        User user = makeUser();
        user.setProgramId(3);
        user.setActivityLevelId(2);
        user.setGoalId(3);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(3)).thenReturn(new Program(3, 3, 2));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeUpperBodyWorkout();
        categoryList.add(mockCatWorkout);

        List<Workout> categoryList2 = new ArrayList<>();
        Workout mockCatWorkout2 = makeLowerBodyWorkout();
        categoryList2.add(mockCatWorkout2);

        List<Workout> categoryList3 = new ArrayList<>();
        Workout mockCatWorkout3 = makeMobilityWorkout();
        categoryList3.add(mockCatWorkout3);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeCardio());
        mockUserWorkouts.add(mockWorkout);

        UserWorkout mockWorkout2 = new UserWorkout();
        mockWorkout2.setUserWorkoutId(2);
        mockWorkout2.setWorkoutDate(LocalDate.now().minusDays(3));
        mockWorkout2.setWorkout(makeCardio());
        mockUserWorkouts.add(mockWorkout2);

        UserWorkout mockWorkout3 = new UserWorkout();
        mockWorkout3.setUserWorkoutId(3);
        mockWorkout3.setWorkout(makeCardio());
        mockWorkout3.setWorkoutDate(LocalDate.now().minusDays(6));
        mockUserWorkouts.add(mockWorkout3);

        UserWorkout mockWorkout4 = new UserWorkout();
        mockWorkout4.setUserWorkoutId(4);
        mockWorkout4.setWorkout(makeCardio());
        mockWorkout4.setWorkoutDate(LocalDate.now().minusDays(4));
        mockUserWorkouts.add(mockWorkout4);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(1)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(1)).thenReturn(categoryList);
        when(workoutRepository.findById(3)).thenReturn(mockCatWorkout2);
        when(workoutRepository.findByCategory(2)).thenReturn(categoryList2);
        when(workoutRepository.findById(11)).thenReturn(mockCatWorkout3);
        when(workoutRepository.findByCategory(4)).thenReturn(categoryList3);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertNotEquals(13, result.getPayload().getWorkoutId());
        assertNotEquals(8, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestCardioModCardio(){
        User user = makeUser();
        user.setProgramId(3);
        user.setActivityLevelId(1);
        user.setGoalId(3);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(3)).thenReturn(new Program(3, 3, 1));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeCardio();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeRestDay());
        mockUserWorkouts.add(mockWorkout);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(8)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(3)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(8, result.getPayload().getWorkoutId());
    }

    @Test
    void shouldSuggestCardioHighCardio(){
        User user = makeUser();
        user.setProgramId(3);
        user.setActivityLevelId(2);
        user.setGoalId(3);
        when(userRepository.findByUserId(1)).thenReturn(user);
        when(programRepository.findById(3)).thenReturn(new Program(3, 3, 2));

        List<Workout> categoryList = new ArrayList<>();
        Workout mockCatWorkout = makeCardio();
        categoryList.add(mockCatWorkout);

        List<UserWorkout> mockUserWorkouts = new ArrayList<>();
        UserWorkout mockWorkout = new UserWorkout();
        mockWorkout.setUserWorkoutId(1);
        mockWorkout.setWorkoutDate(LocalDate.now().minusDays(1));
        mockWorkout.setWorkout(makeRestDay());
        mockUserWorkouts.add(mockWorkout);

        when(userWorkoutRepository.findWorkoutsByUserId(1)).thenReturn(mockUserWorkouts);
        when(workoutRepository.findById(8)).thenReturn(mockCatWorkout);
        when(workoutRepository.findByCategory(3)).thenReturn(categoryList);

        Result<Workout> result = service.suggestWorkout(1);
        assertNotNull(result.getPayload());
        assertTrue(result.isSuccess());
        assertEquals(8, result.getPayload().getWorkoutId());
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