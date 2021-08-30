package capstone.workout_buddy.data;

import capstone.workout_buddy.models.Program;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProgramJdbcTemplateRepositoryTest {

    @Autowired
    ProgramJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){ knownGoodState.set();}

    @Test
    void shouldFindAll(){
        List<Program> all = repository.findAll();
        assertNotNull(all);
        assertEquals(6, all.size());
    }

    @Test
    void shouldFind2ById() {
        Program actual = repository.findById(2);
        assertNotNull(actual);
        assertEquals(1, actual.getActivityLevelId());
        assertEquals(2, actual.getGoalId());
    }

    @Test
    void shouldFindProgramIdByGoalAndActivity() {
        Program actual = repository.findByGoalAndActivity(2, 2);
        assertNotNull(actual);
        assertEquals(5, actual.getProgramId());
    }
}