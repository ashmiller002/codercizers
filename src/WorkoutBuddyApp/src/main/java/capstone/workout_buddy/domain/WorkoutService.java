package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.WorkoutRepository;
import capstone.workout_buddy.models.Workout;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository repository;

    public WorkoutService(WorkoutRepository repository) {
        this.repository = repository;
    }

    public List<Workout> findByCategory(int categoryId){
        return repository.findByCategory(categoryId);
    }

    public Result<Workout> add(Workout workout) {
        Result<Workout> result = validate(workout);
        if (!result.isSuccess()) {
            return result;
        }

        if (workout.getWorkoutId() != 0) {
            result.addMessage("workoutId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        workout = repository.add(workout);
        result.setPayload(workout);
        return result;
    }

    public Result<Workout> update(Workout workout) {
        Result<Workout> result = validate(workout);
        if (!result.isSuccess()) {
            return result;
        }

        if (workout.getWorkoutId() <= 0) {
            result.addMessage("Workout ID must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(workout)) {
            String msg = String.format("workoutId: %s, not found", workout.getWorkoutId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }


    private Result<Workout> validate(Workout workout) {
        Result<Workout> result = new Result<>();
        if (workout == null) {
            result.addMessage("Workout cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(workout.getWorkoutName())) {
            result.addMessage("Workout Name is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(workout.getWorkoutStatus())) {
            result.addMessage("Workout status is required", ResultType.INVALID);
        }

        if (workout.getCategoryId() <= 0) {
            result.addMessage("CategoryId is required.", ResultType.INVALID);
        }

        return result;

    }
}



