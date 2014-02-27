package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends Model {

    @Id
    public Long id;

    @Required
    @Column(name= "username")
    public String userName;

    @Column(name = "name_first")
    public String firstName;

    @Column(name = "name_last")
    public String lastName;

    public static Finder<Long,User> find = new Finder(
            Long.class, User.class
    );

    public static List<User> getUsers() {
        return find.all();
    }

    public static User getUser(Long id) {
        return find.byId(id);
    }

    public static User addUser(User user) {
        user.save();
        return user;
    }

    public static User updateUser(Long id, User user) {
        user.id = id;
        user.update();
        return find.byId(id);
    }

    public static void deleteUser(Long id) {
        find.ref(id).delete();
    }

}
