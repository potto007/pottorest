package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class User extends Model {

    @Id
    public Long id;

    @Required
    public String userName;

    public String firstName;

    public String lastName;

    public static Finder<Long,User> find = new Finder(
            Long.class, User.class
    );

    public static List<User> all() {
        return find.all();
    }

    public static User retrieve(Long id) {
        return find.byId(id);
    }

    public static void create(User user) {
        user.save();
    }

    public static void update(Long id) {
        find.ref(id).update();
    }

    public static void delete(Long id) {
        find.ref(id).delete();
    }

}
