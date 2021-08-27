package capstone.workout_buddy.data;

import capstone.workout_buddy.models.UserWorkout;

public interface UserWorkoutRepository {

    UserWorkout add(UserWorkout userWorkout);
}
