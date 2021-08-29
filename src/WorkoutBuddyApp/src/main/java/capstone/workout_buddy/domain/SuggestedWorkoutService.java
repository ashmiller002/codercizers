package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.ProgramRepository;
import capstone.workout_buddy.data.UserRepository;
import capstone.workout_buddy.data.UserWorkoutRepository;
import capstone.workout_buddy.models.Program;
import capstone.workout_buddy.models.User;
import capstone.workout_buddy.models.Workout;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class SuggestedWorkoutService {
    private final UserWorkoutRepository userWorkoutRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;

    public SuggestedWorkoutService(UserWorkoutRepository userWorkoutRepository, UserRepository userRepository, ProgramRepository programRepository) {
        this.userWorkoutRepository = userWorkoutRepository;
        this.userRepository = userRepository;
        this.programRepository = programRepository;
    }

    public Workout suggestWorkout(int userId){
        Workout workout = new Workout();
        List<Workout> userWorkout = userWorkoutRepository.findWorkoutsByUserId(userId);
        User user = userRepository.findByUserId(userId);
        int programId = user.getProgram();
        Program userProgram = programRepository.findById(programId);

        



        return workout;
    }



}
