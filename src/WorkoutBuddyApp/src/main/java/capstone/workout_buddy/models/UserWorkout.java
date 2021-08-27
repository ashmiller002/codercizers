package capstone.workout_buddy.models;

import java.time.LocalDate;

public class UserWorkout {

    private int userWorkoutId;
    private int userId;
    private int activityId;
    private LocalDate workoutDate;


    public UserWorkout() {
    }

    public UserWorkout(int userWorkoutId, int userId, int activityId, LocalDate workoutDate) {
        this.userWorkoutId = userWorkoutId;
        this.userId = userId;
        this.activityId = activityId;
        this.workoutDate = workoutDate;
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
}
