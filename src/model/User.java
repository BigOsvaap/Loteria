package model;

public class User {

    public static String username;

    private static User instance;

    private User(){}

    public static User getInstance(){
        if (instance == null)
            instance = new User();
        return instance;
    }

}
