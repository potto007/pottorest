package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class Artist extends Model {

    @Id
    public Long id;

    @Required
    @Column(name= "username")
    public String userName;

    @Column(name = "name_first")
    public String firstName;

    @Column(name = "name_last")
    public String lastName;

    public static Finder<Long, Artist> find = new Finder(
            Long.class, Artist.class
    );

    public static List<Artist> getUsers() {
        return find.all();
    }

    public static Artist getUser(Long id) {
        return find.byId(id);
    }

    public static Artist addUser(Artist artist) {
        artist.save();
        return artist;
    }

    public static Artist updateUser(Long id, Artist artist) {
        artist.id = id;
        artist.update();
        return find.byId(id);
    }

    public static void deleteUser(Long id) {
        find.ref(id).delete();
    }

}
