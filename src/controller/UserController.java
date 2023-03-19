package controller;

import model.User;
import model.repository.GBRepository;

import java.util.List;
import java.util.Objects;

import static logger.Log.writerLog;

public class UserController {
    private final GBRepository<User, Long> repository;

    String tag = "INFO";
    String infoLog;
    public UserController(GBRepository<User, Long> repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
        List<User> users = repository.findAll();
        for (User user : users) {
            if (Objects.equals(user.getId(), userId)) {
                infoLog = String.format("Request for User information: id:%s, name:%s, surname: %s, phone: %s "
                        , user.getId(),user.getFirstName(),user.getLastName(),user.getPhone());
                writerLog(infoLog,tag);
                return user;
            }
        }
        throw new RuntimeException("User not found");
    }

//    public User findUserById(long id){
//        return repository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
//    }

    public List<User> getAllUsers(){
        infoLog = "Requested information about all contacts";
        writerLog(infoLog,tag);
        return repository.findAll();
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

//    public void deleteUser(String userId){
//        repository.delete(Long.parseLong(userId));
//    }

}
