package capstone.workout_buddy.models;

public class Program {

    private int programId;
    private int goalId;
    private int activityLevelId;

    public Program() {
    }

    public Program(int programId, int goalId, int activityLevelId) {
        this.programId = programId;
        this.goalId = goalId;
        this.activityLevelId = activityLevelId;
    }

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
