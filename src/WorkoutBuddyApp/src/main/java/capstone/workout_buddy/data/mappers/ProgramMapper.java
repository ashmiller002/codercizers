package capstone.workout_buddy.data.mappers;

import capstone.workout_buddy.models.Program;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgramMapper implements RowMapper<Program> {

    @Override
    public Program mapRow(ResultSet resultSet, int i) throws SQLException {
        Program program = new Program();
        program.setProgramId(resultSet.getInt("program_id"));
        program.setActivityLevelId(resultSet.getInt("activity_level_id"));
        program.setGoalId(resultSet.getInt("goal_id"));
        return program;



    }


}
