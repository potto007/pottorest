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



}
