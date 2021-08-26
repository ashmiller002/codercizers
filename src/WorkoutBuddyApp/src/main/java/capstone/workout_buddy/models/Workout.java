package capstone.workout_buddy.models;

public class Workout {

    private int workoutId;
    private String workoutName;
    private int categoryId;
    private String imageUrl;
    private String workoutStatus;

    public Workout(){};

    public Workout(int categoryId, String workoutName, int workoutId, String workoutStatus) {
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.workoutStatus = workoutStatus;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getWorkoutStatus() {
        return workoutStatus;
    }

    public void setWorkoutStatus(String workoutStatus) {
        this.workoutStatus = workoutStatus;
    }
}
