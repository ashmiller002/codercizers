package capstone.workout_buddy.data.mappers;

import capstone.workout_buddy.models.Workout;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutMapper implements RowMapper<Workout> {

    @Override
    public Workout mapRow(ResultSet resultSet, int i) throws SQLException {
        Workout workout = new Workout();
        workout.setWorkoutId(resultSet.getInt("workout_id"));
        workout.setWorkoutName(resultSet.getString("workout_name"));
        workout.setCategoryId(resultSet.getInt("category_id"));
        workout.setImageUrl(resultSet.getString("image_url"));
        workout.setWorkoutStatus(resultSet.getString("workout_status"));

        return workout;
    }

}
