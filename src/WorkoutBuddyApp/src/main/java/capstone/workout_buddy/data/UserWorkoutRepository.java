package capstone.workout_buddy.data;

import capstone.workout_buddy.models.UserWorkout;
import capstone.workout_buddy.models.Workout;

import java.util.List;

public interface UserWorkoutRepository {

    UserWorkout add(UserWorkout userWorkout);

    public List<Workout> findWorkoutsByUserId(int userId);
}
