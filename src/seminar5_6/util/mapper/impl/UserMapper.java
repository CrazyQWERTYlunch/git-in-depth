package seminar5_6.util.mapper.impl;

import seminar5_6.model.User;
import seminar5_6.util.mapper.Digitable;
import seminar5_6.util.mapper.Mapper;

public class UserMapper implements Mapper<User, String>, Digitable {
    @Override
    public String toInput(User user) {
        return String.format("%s:%s:%s:%s", user.getId(), user.getFirstName(), user.getLastName(), user.getPhone());
    }

    @Override
    public User toOutput(String s) {
        String[] lines = s.split(":");
        long id;
        if (isDigit((lines[0]))) {
            id = Long.parseLong(lines[0]);
            return new User(id, lines[1], lines[2], lines[3]);
        }
        throw new NumberFormatException("Id must be a large number");
    }

    @Override
    public boolean isDigit(String s) throws NumberFormatException {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
