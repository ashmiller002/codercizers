package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.UserRepository;
import capstone.workout_buddy.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {this.repository = repository; }

    public User findByUserId(int userId){
        return repository.findByUserId(userId);
    }

    public User findByLoginId(String loginId){
        return repository.findByLoginId(loginId);
    }

    //validation for add user
    // no duplicate emails
    // date must be in the past for birthday
    //

    public Result<User> add(User user){
        Result<User> result = validation(user);

        //setting the program ID using the activity/level goal from the ui....
        //user.setProgramId(generateProgramId(goalId, activityId));

        if(!result.isSuccess()) {
            return result;
        }



        user = repository.add(user);
        result.setPayload(user);
        return result;
    }


    private Result<User> validation(User user){
        Result<User> result = new Result<>();

        if (user == null){
            result.addMessage("No user information provided.", ResultType.INVALID);
            return result;
        }

        result = validateDupEmails(result, user);





        return result;
    }

    private Result<User> validateDupEmails(Result<User> result, User user){
        List<User> users = repository.findAll();
        for (User u: users){
            if (u.getEmail().equalsIgnoreCase(user.getEmail())){
                result.addMessage("This email is already registered to an account", ResultType.INVALID);
            }
        }

        return result;
    }

    private Result<User> validateDateBirth(Result<User> result, User user){
        return null;
    }

    private int generateProgramId(int goalId, int activityId){
        return 0;
    }






}
