package homework6;

import homework6.impl.User;

public class Main{
    public static void main(String[] args){
        User user = new User("Bob");
        user.report();
        user.save();
    }
}