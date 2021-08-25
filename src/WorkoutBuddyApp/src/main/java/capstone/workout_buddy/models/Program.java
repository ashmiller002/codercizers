package capstone.workout_buddy.models;

public class Program {

    private int programId;
    private int activityLevelId;
    private int goalId;

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getActivityLevelId() {
        return activityLevelId;
    }

    public void setActivityLevelId(int activityLevelId) {
        this.activityLevelId = activityLevelId;
    }

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }
}
