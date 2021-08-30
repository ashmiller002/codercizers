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


import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
        List<UserWorkout> userWorkout = userWorkoutRepository.findWorkoutsByUserId(userId);
        List<Workout> workoutList = workoutRepository.findAll();
        User user = userRepository.findByUserId(userId);
        Program userProgram = programRepository.findById(user.getProgram());
        int suggestedWorkoutId = 5;

        Workout priorDayWorkout = new Workout();
        List<UserWorkout> recentWorkouts = new ArrayList<>();
        HashMap<Integer, Integer> categoryCounts = countPerCategory(recentWorkouts);

        for (UserWorkout w: userWorkout){
            if (w.getWorkoutDate().isAfter((LocalDate.now().minusDays(6)))){
                //getting a list of recent workouts
                recentWorkouts.add(w);
            }
            if (w.getWorkoutDate().equals(LocalDate.now().minusDays(1))){
                //saving prior day workout to variable
                priorDayWorkout = w.getWorkout();
            }
        }

        switch (userProgram.getGoalId()){
            case 1:
                //strength goal
                if (userProgram.getActivityLevelId() == 1){
                    suggestedWorkoutId = strengthWorkoutModerate(recentWorkouts, priorDayWorkout, categoryCounts);
                    }
                else {
                    suggestedWorkoutId = strengthWorkoutHigh(recentWorkouts, priorDayWorkout, categoryCounts);
                }
                break;
            case 2:
                //mobility goal
                break;
            case 3:
                //cardio goal
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + userProgram.getGoalId());
        }


        //set suggestedWorkout based on workoutId
        Workout suggestedWorkout = workoutRepository.findById(suggestedWorkoutId);

        return suggestedWorkout;
    }
    

    private int strengthWorkoutModerate(List<UserWorkout> recentWorkouts, Workout priorDayWorkout, HashMap<Integer, Integer> categoryCounts){
        //alternate rest day with exercise - DONE
        //one upper one lower per week
        //recommend 50% chance between upper and lower
        int suggestedWorkoutId = 0;
        int countUpper = 0, countLower = 0, nonStrength = 0;

        if (priorDayWorkout.getCategoryId() != 5){
            List<Workout> categoryWorkouts = workoutRepository.findByCategory(5);
            suggestedWorkoutId = (int)(Math.random() * categoryWorkouts.size()) + 1;
            return suggestedWorkoutId;
        }



        return suggestedWorkoutId;
    }

    private int strengthWorkoutHigh(List<UserWorkout> recentWorkouts, Workout priorDayWorkout, HashMap<Integer, Integer> categoryCounts){


        return 0;
    }

    private HashMap<Integer, Integer> countPerCategory(List<UserWorkout> recentWorkouts){
        HashMap<Integer, Integer> categoryCounts = new HashMap<>();
        categoryCounts.put(1,0);
        categoryCounts.put(2,0);
        categoryCounts.put(3,0);
        categoryCounts.put(4,0);
        categoryCounts.put(5,0);

        for (UserWorkout w: recentWorkouts){
            if (w.getWorkout().getCategoryId() == 1){
                categoryCounts.put(1, categoryCounts.get(1)+1);
            } else if (w.getWorkout().getCategoryId() == 2){
                categoryCounts.put(2, categoryCounts.get(2)+1);
            }
            else if (w.getWorkout().getCategoryId() == 3){
                categoryCounts.put(3, categoryCounts.get(3)+1);
            }
            else if (w.getWorkout().getCategoryId() == 4){
                categoryCounts.put(4, categoryCounts.get(4)+1);
            } else {
                categoryCounts.put(5, categoryCounts.get(5)+1);
            }
        }
        return categoryCounts;
    }






}
