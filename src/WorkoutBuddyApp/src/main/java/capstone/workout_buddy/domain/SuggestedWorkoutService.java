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

    public Result<Workout> suggestWorkout(int userId){
        //add validation for user?  like if user not found....
        Result<Workout> result = new Result<>();
        List<UserWorkout> userWorkout = userWorkoutRepository.findWorkoutsByUserId(userId);
        User user = userRepository.findByUserId(userId);
        Program userProgram = programRepository.findById(user.getProgram());
        int suggestedWorkoutId = 5;

        Workout priorDayWorkout = new Workout();
        List<UserWorkout> recentWorkouts = new ArrayList<>();

        if (userWorkout != null){
            for (UserWorkout w: userWorkout){
                if (w.getWorkoutDate().isAfter((LocalDate.now().minusDays(7)))){
                    //getting a list of recent workouts
                    recentWorkouts.add(w);
                }
                if (w.getWorkoutDate().equals(LocalDate.now().minusDays(1))){
                    //saving prior day workout to variable
                    priorDayWorkout = w.getWorkout();
                }
            }
        }

        HashMap<Integer, Integer> categoryCounts = countPerCategory(recentWorkouts);

        switch (userProgram.getGoalId()){
            case 1:
                //strength goal
                if (userProgram.getActivityLevelId() == 1){
                    suggestedWorkoutId = strengthWorkoutModerate(priorDayWorkout, categoryCounts);
                    }
                else {
                    suggestedWorkoutId = strengthWorkoutHigh(priorDayWorkout, categoryCounts);
                }
                break;
            case 2:
                //mobility goal
                break;
            case 3:
                //cardio goal
                break;
            default:
                //default workout suggestion?
                throw new IllegalStateException("Unexpected value: " + userProgram.getGoalId());
        }


        //set suggestedWorkout based on workoutId
        result.setPayload(workoutRepository.findById(suggestedWorkoutId));

        return result;
    }


    private int strengthWorkoutModerate(Workout priorDayWorkout, HashMap<Integer, Integer> categoryCounts){
        //alternate rest day with exercise - DONE
        //one upper one lower per week
        //recommend 50% chance between upper and lower
        int suggestedWorkoutId = 5;
        List<Workout> categoryWorkouts = new ArrayList<>();


        if (priorDayWorkout.getCategoryId() != 5 && priorDayWorkout.getCategoryId() != 0){
            categoryWorkouts = workoutRepository.findByCategory(5);
            suggestedWorkoutId = categoryWorkouts.get((int)(Math.random() * categoryWorkouts.size())).getWorkoutId();
            return suggestedWorkoutId;
        }

        if (categoryCounts.get(1) == 0 && priorDayWorkout.getCategoryId() != 1){
            //suggest upper body cat 1
            categoryWorkouts = workoutRepository.findByCategory(1);

        } else if (categoryCounts.get(2) == 0 && priorDayWorkout.getCategoryId() != 2){
            //suggest lower body cat 2
            categoryWorkouts = workoutRepository.findByCategory(2);
        } else if (categoryCounts.get(1) == 1 && categoryCounts.get(2) == 1){
            //do a random upper/lower
            int cat = (int)(Math.random() * 2) +1;
            categoryWorkouts = workoutRepository.findByCategory(cat);
        }else {
            categoryWorkouts = workoutRepository.findByCategory(5);
        }

        categoryWorkouts.removeIf(workout -> (workout.getWorkoutName().contains("Custom")));
        suggestedWorkoutId = categoryWorkouts.get((int)(Math.random() * categoryWorkouts.size())).getWorkoutId();
        return suggestedWorkoutId;
    }

    private int strengthWorkoutHigh(Workout priorDayWorkout, HashMap<Integer, Integer> categoryCounts){
        //two upper, two lower per week
        // no consecutive upper or lower
        // fifth 50% mobility or cardio
        int suggestedWorkoutId = 5;
        List<Workout> categoryWorkouts = new ArrayList<>();

        if (categoryCounts.get(1) < 2 && priorDayWorkout.getCategoryId() != 1){
            //recommend upper body
            categoryWorkouts = workoutRepository.findByCategory(1);

        }else if (categoryCounts.get(2) < 2 && priorDayWorkout.getCategoryId() != 2){
            //recommend lower body
            categoryWorkouts = workoutRepository.findByCategory(2);
        }else if (categoryCounts.get(3) <1 && categoryCounts.get(4) <1){
            //recommend mobiltiy or cardio
            int cat = (int)(Math.random() * 2) +3;
            categoryWorkouts = workoutRepository.findByCategory(cat);
        }else {
            //recommend rest day
            categoryWorkouts = workoutRepository.findByCategory(5);
        }

        categoryWorkouts.removeIf(workout -> (workout.getWorkoutName().contains("Custom")));
        suggestedWorkoutId = categoryWorkouts.get((int)(Math.random() * categoryWorkouts.size())).getWorkoutId();
        return suggestedWorkoutId;
    }

    private HashMap<Integer, Integer> countPerCategory(List<UserWorkout> recentWorkouts){
        HashMap<Integer, Integer> categoryCounts = new HashMap<>();
        categoryCounts.put(1,0);
        categoryCounts.put(2,0);
        categoryCounts.put(3,0);
        categoryCounts.put(4,0);
        categoryCounts.put(5,0);
        int count = 0;

        if (recentWorkouts != null){
            for (UserWorkout w: recentWorkouts){
                if (w.getWorkout().getCategoryId() == 1){
                    count = categoryCounts.get(1);
                    count++;
                    categoryCounts.put(1, count);
                } else if (w.getWorkout().getCategoryId() == 2){
                    count = categoryCounts.get(2);
                    count++;
                    categoryCounts.put(2, count);
                }
                else if (w.getWorkout().getCategoryId() == 3){
                    count = categoryCounts.get(3);
                    count++;
                    categoryCounts.put(3, count);
                }
                else if (w.getWorkout().getCategoryId() == 4){
                    count = categoryCounts.get(4);
                    count++;
                    categoryCounts.put(4, count);
                } else {
                    count = categoryCounts.get(5);
                    count++;
                    categoryCounts.put(5, count);
                }
            }
        }
        return categoryCounts;
    }






}
