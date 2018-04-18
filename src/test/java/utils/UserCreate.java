package utils;

import users.User;

public class UserCreate {

    public static User createDummyUser() {
        return new User().setName("Vitalik")
                .setAge(22)
                .setSalary(3000);
    }

    public static User creatingUserWithPresentName() {
        return new User().setId(4)
                .setName("Silvia")
                .setAge(50)
                .setSalary(40000);
    }

    public static User createDummyUser2() {
        return new User()
                .setName("Maxim")
                .setAge(40)
                .setSalary(49000);
    }
}
