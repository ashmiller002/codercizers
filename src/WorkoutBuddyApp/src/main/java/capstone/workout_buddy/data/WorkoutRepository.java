package capstone.workout_buddy.data;

import capstone.workout_buddy.models.Workout;

import java.util.List;

public interface WorkoutRepository {
    Workout add(Workout workout);

    List<Workout> findByCategory(int categoryId);

    boolean update(Workout workout);




}
