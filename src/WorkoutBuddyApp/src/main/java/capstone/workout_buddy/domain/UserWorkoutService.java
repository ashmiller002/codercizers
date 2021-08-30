package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.UserWorkoutRepository;
import capstone.workout_buddy.models.UserWorkout;
import capstone.workout_buddy.models.Workout;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWorkoutService {

    private final UserWorkoutRepository repository;

    public UserWorkoutService(UserWorkoutRepository repository) {
        this.repository = repository;
    }

    public Result<UserWorkout> add(UserWorkout userWorkout){
        Result<UserWorkout> result = validate(userWorkout);
        if (!result.isSuccess()) {
            return result;
        }

        if (userWorkout.getUserWorkoutId() != 0) {
            result.addMessage("User Workout Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        userWorkout = repository.add(userWorkout);
        result.setPayload(userWorkout);
        return result;
    }

    public List<UserWorkout> findWorkoutsByUserId(int userId){
        return repository.findWorkoutsByUserId(userId);
    }

    private Result<UserWorkout> validate(UserWorkout userWorkout) {
        Result<UserWorkout> result = new Result<>();
        if (userWorkout == null) {
            result.addMessage("User Workout cannot be null", ResultType.INVALID);
            return result;
        }

        if (userWorkout.getWorkout()==null) {
            result.addMessage("User workout is required", ResultType.INVALID);
        }

        if (userWorkout.getWorkoutDate()==null) {
            result.addMessage("Workout Date is required", ResultType.INVALID);
        }

        if (userWorkout.getUserId() <= 0) {
            result.addMessage("UserId is required.", ResultType.INVALID);
        }

        return result;

    }
}
