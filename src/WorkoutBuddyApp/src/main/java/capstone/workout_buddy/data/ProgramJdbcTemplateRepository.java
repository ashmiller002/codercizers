package capstone.workout_buddy.data;

import capstone.workout_buddy.data.mappers.ProgramMapper;
import capstone.workout_buddy.models.Program;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProgramJdbcTemplateRepository implements ProgramRepository{

    private final JdbcTemplate jdbcTemplate;

    public ProgramJdbcTemplateRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Program> findAll(){
        final String sql = "select program_id, activity_level_id, goal_id " +
                "from program " +
                "where program = ?;";

        return jdbcTemplate.query(sql, new ProgramMapper());

    }

    @Override
    public Program findByGoalAndActivity(int goalId, int activityId){

        final String sql = "select activity_level_id, goal_id " +
                "from program " +
                "where activity_level_id = ? and goal_id = ?;";

        return jdbcTemplate.query(sql, new ProgramMapper(), activityId, goalId)
                .stream()
                .findFirst().orElse(null);
    }
}
