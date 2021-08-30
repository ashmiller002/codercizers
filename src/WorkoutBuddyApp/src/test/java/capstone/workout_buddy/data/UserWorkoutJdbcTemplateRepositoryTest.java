package capstone.workout_buddy.data;

import capstone.workout_buddy.models.UserWorkout;
import capstone.workout_buddy.models.Workout;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserWorkoutJdbcTemplateRepositoryTest {
    @Autowired
    UserWorkoutJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void shouldAddUserWorkout() {
        UserWorkout actual = new UserWorkout();
        Workout workout = new Workout(1,"upperBodyTest", 1,  "enable");
        actual.setWorkout(workout);
        actual.setUserWorkoutId(2);
        actual.setUserId(1);
        actual.setActivityId(1);
        actual.setWorkoutDate(Date.valueOf("2021-8-02"));
        repository.add(actual);
        assertNotNull(actual);
        assertEquals(Date.valueOf("2021-8-02"), actual.getWorkoutDate());
        assertEquals(1 ,actual.getUserId());
    }

    @Test
    void shouldFindWorkoutsByUserId() {
        List<UserWorkout> userWorkouts = repository.findWorkoutsByUserId(1);
        assertNotNull(userWorkouts);
    }
}