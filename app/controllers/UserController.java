package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class UserController extends Controller{

    public static Result getUsers() {
        List<User> users = Database.getUsers();
        return ok(Json.toJson(users));
    }

    public static Result getUser(Long id) {
        User user = Database.getUser(id);
        return user == null ? notFound() : ok(Json.toJson(user));
    }

    public static Result createUser() {
        User newUser = Json.fromJson(request().body().asJson(), User.class);
        User inserted = Database.addUser(newUser);
        return created(Json.toJson(inserted));
    }

    public static Result updateUser(Long id) {
        User user = Json.fromJson(request().body().asJson(), User.class);
        User updated = Database.updateUser(id, user);
        return ok(Json.toJson(updated));
    }

    public static Result deleteUser(Long id) {
        Database.deleteUser(id);
        return noContent(); // http://stackoverflow.com/a/2342589/1415732
    }
}
