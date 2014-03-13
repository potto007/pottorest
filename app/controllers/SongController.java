package controllers;

import java.util.*;

import exceptions.*;
import play.mvc.*;
import play.libs.Json;

import models.*;

import javax.persistence.PersistenceException;

public class SongController extends Controller{

    public static Result getSongs() {
        List<Song> songs = Song.getSongs();
        return ok(Json.toJson(songs));
    }

    public static Result getSong(Long id) {
        Song song = Song.getSong(id);
        return song == null ? notFound() : ok(Json.toJson(song));
    }

    public static Result getArtistSongs(Long artistId) {
        List<Song> songs = Song.getArtistSongs(artistId);
        return ok(Json.toJson(songs));
    }

    public static Result getArtistAlbumSongs(Long artistId, Long albumId) {
        List<Song> songs = Song.getAlbumSongs(albumId);
        return ok(Json.toJson(songs));
    }

    public static Result createArtistAlbumSong(Long artistId, Long albumId) {
        Song newSong = Json.fromJson(request().body().asJson(), Song.class);
        try {
            Song inserted = Song.addSong(newSong, artistId, albumId);
            return created(Json.toJson(inserted));
        } catch (NullProvidedException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(400,e.getMessage());
            return badRequest(errorHandler.getMessage());
        } catch (PersistenceException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(409,e.getMessage());
            return badRequest(errorHandler.getMessage());
        }
    }

    public static Result updateSong(Long id) {
        Song song = Json.fromJson(request().body().asJson(), Song.class);
        try {
            Song updated = Song.updateSong(id, song);
            return ok(Json.toJson(updated));
        } catch (PersistenceException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(409,e.getMessage());
            return badRequest(errorHandler.getMessage());
        }
    }

    public static Result deleteSong(Long id) {
        Song.deleteSong(id);
        return noContent();
    }

}
