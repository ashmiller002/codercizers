package capstone.workout_buddy.data.mappers;

import capstone.workout_buddy.models.ActivityLevel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityLevelMapper implements RowMapper<ActivityLevel> {

    @Override
    public ActivityLevel mapRow(ResultSet resultSet, int i) throws SQLException{
        ActivityLevel activityLevel = new ActivityLevel();
        activityLevel.setActivityLevelId(resultSet.getInt("activity_level_id"));
        activityLevel.setActivityLevelName(resultSet.getString("activity_level_name"));
        return activityLevel;
    }
}
