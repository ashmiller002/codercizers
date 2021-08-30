package capstone.workout_buddy.models;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserWorkout {

    private int userWorkoutId;
    private int userId;
    private int activityId;
    private LocalDate workoutDate;
    private List<Workout> workouts = new ArrayList<>();
    private Workout workout;

    public UserWorkout() {
    }

    public UserWorkout(int userWorkoutId, int userId, int activityId, LocalDate workoutDate, Workout workout) {
        this.userWorkoutId = userWorkoutId;
        this.userId = userId;
        this.activityId = activityId;
        this.workoutDate = workoutDate;
        this.workout = workout;
    }

    public int getUserWorkoutId() {
        return userWorkoutId;
    }

    public void setUserWorkoutId(int userWorkoutId) {
        this.userWorkoutId = userWorkoutId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public LocalDate getWorkoutDate() {
        return workoutDate;
    }

    public void setWorkoutDate(LocalDate workoutDate) {
        this.workoutDate = workoutDate;
    }

    public List<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }
}
