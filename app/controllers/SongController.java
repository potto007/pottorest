package controllers;

import java.util.*;

import play.mvc.*;
import play.libs.Json;

import models.*;

public class SongController extends Controller{

    public static Result getSongs() {
        List<Song> songs = Song.getSongs();
        return ok(Json.toJson(songs));
    }

    public static Result getSong(Long id) {
        Song song = Song.getSong(id);
        return song == null ? notFound() : ok(Json.toJson(song));
    }

    public static Result createSong() {
        Song newSong = Json.fromJson(request().body().asJson(), Song.class);
        Song inserted = Song.addSong(newSong);
        return created(Json.toJson(inserted));
    }

    public static Result updateSong(Long id) {
        Song song = Json.fromJson(request().body().asJson(), Song.class);
        Song updated = Song.updateSong(id, song);
        return ok(Json.toJson(updated));
    }

    public static Result deleteSong(Long id) {
        Song.deleteSong(id);
        return noContent();
    }

}
