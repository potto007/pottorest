package controllers;

import java.util.*;

import play.*;
import play.mvc.*;
import play.data.*;
import play.libs.Json;

import models.*;

import views.html.*;

public class UserController extends Controller{

    public static void getUsers() {
        List<User> users = User.getUsers();
//        return ok(Json.toJson(users));
        render(users);
    }

/*
    public static Result getUsers() {
        List<User> users = User.getUsers();
        return ok(Json.toJson(users));
    }*/

    public static Result getUser(Long id) {
        User user = User.getUser(id);
        return user == null ? notFound() : ok(Json.toJson(user));
    }

    public static Result createUser() {
        User newUser = Json.fromJson(request().body().asJson(), User.class);
        User inserted = User.addUser(newUser);
        return created(Json.toJson(inserted));
    }

    public static Result updateUser(Long id) {
        User user = Json.fromJson(request().body().asJson(), User.class);
        User updated = User.updateUser(id, user);
        return ok(Json.toJson(updated));
    }

    public static Result deleteUser(Long id) {
        User.deleteUser(id);
        return noContent();
    }

    @Util
    @Catch(value = Exception.class)
    public static Result throwException(Exception e) {
        return TODO;
    }
}
