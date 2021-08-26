package capstone.workout_buddy.domain;

import capstone.workout_buddy.data.UserRepository;
import capstone.workout_buddy.models.User;
import org.springframework.stereotype.Service;

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

    public User add(User user){

        return null;
    }


}
