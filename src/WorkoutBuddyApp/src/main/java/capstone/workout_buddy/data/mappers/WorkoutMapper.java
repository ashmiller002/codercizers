package capstone.workout_buddy.data.mappers;

import capstone.workout_buddy.models.Workout;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkoutMapper implements RowMapper<Workout> {

    @Override
    public Workout mapRow(ResultSet resultSet, int i) throws SQLException {
        Workout workout = new
    }

}
