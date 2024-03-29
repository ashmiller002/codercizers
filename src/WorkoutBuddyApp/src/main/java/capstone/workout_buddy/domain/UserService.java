package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.ProgramRepository;
import capstone.workout_buddy.data.UserRepository;
import capstone.workout_buddy.models.Program;
import capstone.workout_buddy.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final ProgramRepository programRepository;

    public UserService(UserRepository repository, ProgramRepository programRepository) {this.repository = repository;
        this.programRepository = programRepository;
    }

    public User findByUserId(int userId){
        return repository.findByUserId(userId);
    }

    public User findByLoginId(String loginId){
        User user = repository.findByLoginId(loginId);
        Program program = programRepository.findById(user.getProgram());
        user.setActivityLevelId(program.getActivityLevelId());
        user.setGoalId(program.getGoalId());
        
        return user;
    }

    public Result<User> add(User user){
        Result<User> result = validation(user);

        if(!result.isSuccess()) {
            return result;
        }

        if (user.getProgram() == 0){
            Program program = programRepository.findByGoalAndActivity(user.getGoalId(), user.getActivityLevelId());
            user.setProgramId(program.getProgramId());
        }

        user = repository.add(user);
        result.setPayload(user);
        return result;
    }

    public Result<User> update(User user){
        Result<User> result = validation(user);

        if(!result.isSuccess()) {
            return result;
        }

        Program program = programRepository.findByGoalAndActivity(user.getGoalId(), user.getActivityLevelId());
        user.setProgramId(program.getProgramId());

        if (user.getUserId() <= 0){
            result.addMessage("UserId must be set for update operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(user)){
            String msg = String.format("UserId not found.");
            result.addMessage(msg, ResultType.NOT_FOUND);
            return result;
        }
        result.setPayload(user);
        return result;
    }



    private Result<User> validation(User user){
        Result<User> result = new Result<>();

        if (user == null){
            result.addMessage("No user information provided.", ResultType.INVALID);
            return result;
        }
        if (Validations.isNullOrBlank(user.getEmail())){
            result.addMessage("Cannot add user without valid email.", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(user.getFirstName())){
            result.addMessage("First name required.", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(user.getLastName())){
            result.addMessage("Last name required.", ResultType.INVALID);
            return result;
        }


        result = validateDateBirth(result, user);
        if (!result.isSuccess()){
            return result;
        }

        result = validateDupEmails(result, user);

        return result;
    }

    private Result<User> validateDupEmails(Result<User> result, User user){
        List<User> users = repository.findAll();
        for (User u: users){
            if (u.getUserId() != user.getUserId()){
                if (u.getEmail().equalsIgnoreCase(user.getEmail())){
                    result.addMessage("This email is already registered to an account", ResultType.INVALID);
                    return result;
                }
            }
        }
        return result;
    }

    private Result<User> validateDateBirth(Result<User> result, User user){
        if (user.getDateBirth() == null){
            result.addMessage("Date of birth required.", ResultType.INVALID);
            return result;
        }
        if (user.getDateBirth().isEqual(LocalDate.now()) || user.getDateBirth().isAfter(LocalDate.now())){
            result.addMessage("Must provide valid date of birth.", ResultType.INVALID);
        }
        return result;
    }







}
