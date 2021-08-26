package capstone.workout_buddy.models;

public enum ActivityLevel {

    INFREQUENT(1, "infrequent"),
    FREQUENT(2, "frequent");


    private int activityLevelId;
    private String activityLevelName;

    private ActivityLevel(int activityLevelId, String activityLevelName) {
        this.activityLevelId = activityLevelId;
        this.activityLevelName = activityLevelName;
    }

    public static ActivityLevel fromActivityId(int activityLevelId){
        for (ActivityLevel option: ActivityLevel.values()){
            if (option.getActivityLevelId() == activityLevelId){
                return option;
            }
        }
        return null;
    }

    public int getActivityLevelId() {
        return activityLevelId;
    }

    public String getActivityLevelName() {
        return activityLevelName;
    }


}
