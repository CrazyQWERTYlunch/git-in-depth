package model.repository.impl;

import model.User;
import model.dao.impl.FileOperation;
import model.repository.GBRepository;
import util.mapper.impl.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static logger.Log.writerLog;

public class UserRepository implements GBRepository<User, Long> {
    private final UserMapper mapper;
    private final FileOperation operation;

    String infoLog;
    String tag = "INFO";

    public UserRepository(FileOperation operation) {
        this.mapper = new UserMapper();
        this.operation = operation;
    }

    @Override
    public List<User> findAll() {
        List<String> lines = operation.readAll();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.toOutput(line));
        }
        return users;
    }
    @Override
    public User create(User user) {
        List<User> users = findAll();
        long max = 0L;
        for (User u : users) {
            long id = u.getId();
            if (max < id){
                max = id;
            }
        }
        long next = max + 1;
        user.setId(next);
        users.add(user);
        write(users);
        infoLog = String.format("Added new contact  id: %s, name: %s, surname: %s phone: %s",user.getId()
                ,user.getFirstName(),user.getLastName(),user.getPhone());
        writerLog(infoLog,tag);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(Long userId, User update) {
        List<User> users = findAll();
        User editUser = users.stream()
                .filter(u -> u.getId()
                        .equals(userId))
                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));

        infoLog = String.format("Contact:  id: %s, name: %s, surname: %s phone: %s",editUser.getId()
                ,editUser.getFirstName(),editUser.getLastName(),editUser.getPhone());

        editUser.setFirstName(update.getFirstName());
        editUser.setLastName(update.getLastName());
        editUser.setPhone(update.getPhone());
        write(users);

        infoLog = infoLog + String.format(" was changed to: id: %s, name: %s, surname: %s phone: %s",editUser.getId()
                ,editUser.getFirstName(),editUser.getLastName(),editUser.getPhone());

        writerLog(infoLog,tag);
        return Optional.of(update);
    }


    public UserMapper getMapper() {
        return mapper;
    }
//    @Override
//    public Optional<User> delete(Long userId) {
//        List<User> users = findAll();
//        User editUser = users.stream()
//                .filter(u -> u.getId()
//                        .equals(userId))
//                .findFirst().orElseThrow(() -> new RuntimeException("User not found"));
//        infoLog = String.format("Contact:  id: %s, name: %s, surname: %s phone: %s  has been deleted",editUser.getId()
//                ,editUser.getFirstName(),editUser.getLastName(),editUser.getPhone());
//        editUser.setFirstName("");
//        editUser.setLastName("");
//        editUser.setPhone("");
//        write(users);
//        writerLog(tag,infoLog);
//        return Optional.of(editUser);
//    }

    @Override
    public Optional<User> delete(Long id) {
        return Optional.empty();
    }

    private void write(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User u: users) {
            lines.add(mapper.toInput(u));
        }
        operation.saveAll(lines);
    }

}
