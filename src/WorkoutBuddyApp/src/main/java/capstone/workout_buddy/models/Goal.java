package capstone.workout_buddy.models;

public enum Goal {

    STRENGTH(1, "Strength"),
    MOBILITY(2, "Mobility"),
    WEIGHT_LOSS(3, "Weight Loss");

    private int goalId;
    private String goalName;

    private Goal(int goalId, String goalName) {
        this.goalId = goalId;
        this.goalName = goalName;
    }

    public static Goal fromGoalId(int goalId){
        for (Goal option: Goal.values()){
            if (option.getGoalId() == goalId){
                return option;
            }
        }
        return null;
    }

    public int getGoalId() {
        return goalId;
    }

    public String getGoalName() {
        return goalName;
    }
}
