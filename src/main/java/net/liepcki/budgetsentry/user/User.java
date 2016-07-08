package net.liepcki.budgetsentry.user;

import org.springframework.data.annotation.Id;

/**
 * Created by gregorry on 08.07.2016.
 */
public class User {

    @Id
    private String id;
    private String name;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("User[id=%s, name='%s']", id, name);
    }

}
