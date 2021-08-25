package capstone.workout_buddy.data.mappers;

import capstone.workout_buddy.models.Goal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GoalMapper implements RowMapper<Goal> {

    @Override
    public Goal mapRow(ResultSet resultSet, int i) throws SQLException {
        Goal goal = new Goal();
        goal.setGoalId(resultSet.getInt("goal_id"));
        goal.setGoalName(resultSet.getString("goal_name"));
        return goal;
    }
}
