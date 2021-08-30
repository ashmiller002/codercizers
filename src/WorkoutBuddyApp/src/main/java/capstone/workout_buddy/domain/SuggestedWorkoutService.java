package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.ProgramRepository;
import capstone.workout_buddy.data.UserRepository;
import capstone.workout_buddy.data.UserWorkoutRepository;
import capstone.workout_buddy.data.WorkoutRepository;
import capstone.workout_buddy.models.Program;
import capstone.workout_buddy.models.User;
import capstone.workout_buddy.models.UserWorkout;
import capstone.workout_buddy.models.Workout;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SuggestedWorkoutService {
    private final UserWorkoutRepository userWorkoutRepository;
    private final UserRepository userRepository;
    private final ProgramRepository programRepository;
    private final WorkoutRepository workoutRepository;

    public SuggestedWorkoutService(UserWorkoutRepository userWorkoutRepository, UserRepository userRepository, ProgramRepository programRepository, WorkoutRepository workoutRepository) {
        this.userWorkoutRepository = userWorkoutRepository;
        this.userRepository = userRepository;
        this.programRepository = programRepository;
        this.workoutRepository = workoutRepository;
    }

    public Workout suggestWorkout(int userId){
        Workout workout = new Workout();
        List<UserWorkout> userWorkout = userWorkoutRepository.findWorkoutsByUserId(userId);
        User user = userRepository.findByUserId(userId);
        int programId = user.getProgram();
        Program userProgram = programRepository.findById(programId);

        UserWorkout priorDayWorkout = new UserWorkout();
        List<UserWorkout> recentWorkouts = new ArrayList<>();

        for (UserWorkout w: userWorkout){
            if (w.getWorkoutDate().after(LocalDate.now().minusDays(6))){
                recentWorkouts.add(w);
            }

        }

        switch (userProgram.getGoalId()){
            case 1:
                //strength goal
                break;
            case 2:
                //mobility goal
                break;
            case 3:
                //cardio goal
                break;
        }



        return workout;
    }

    //strength category



}
