package capstone.workout_buddy.data;

import capstone.workout_buddy.models.Program;

import java.util.List;

public interface ProgramRepository {

    List<Program> findAll();

    Program findByGoalAndActivity(int goalId, int activityId);


}
