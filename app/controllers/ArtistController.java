package controllers;

import java.util.*;

import play.mvc.*;
import play.libs.Json;

import models.*;

public class ArtistController extends Controller{

    public static Result getArtists() {
        List<Artist> artists = Artist.getUsers();
        return ok(Json.toJson(artists));
    }

    public static Result getArtist(Long id) {
        Artist artist = Artist.getUser(id);
        return artist == null ? notFound() : ok(Json.toJson(artist));
    }

    public static Result createArtist() {
        Artist newArtist = Json.fromJson(request().body().asJson(), Artist.class);
        Artist inserted = Artist.addUser(newArtist);
        return created(Json.toJson(inserted));
    }

    public static Result updateArtist(Long id) {
        Artist artist = Json.fromJson(request().body().asJson(), Artist.class);
        Artist updated = Artist.updateUser(id, artist);
        return ok(Json.toJson(updated));
    }

    public static Result deleteArtist(Long id) {
        Artist.deleteUser(id);
        return noContent();
    }

}
