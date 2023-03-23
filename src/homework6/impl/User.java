package homework6.impl;

import homework6.Reportable;
import homework6.Saveable;

public class User implements Saveable, Reportable {
    private final String name;

    public User(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    @Override
    public void save(){
        System.out.println("Save user: " + getName());
    }

    @Override
    public void report(){
        System.out.println("Report for user: " + name);
    }
}
