package controllers;

import java.util.*;

import exceptions.WebAppExceptionHandler;
import play.mvc.*;
import play.libs.Json;

import models.*;

import javax.persistence.PersistenceException;

public class AlbumController extends Controller{

    public static Result getAlbums() {
        List<Album> albums = Album.getAlbums();
        return ok(Json.toJson(albums));
    }

    public static Result getAlbum(Long id) {
        Album album = Album.getAlbum(id);
        return album == null ? notFound() : ok(Json.toJson(album));
    }

    public static Result createAlbum() {
        Album newAlbum = Json.fromJson(request().body().asJson(), Album.class);
        try {
            Album inserted = Album.addAlbum(newAlbum);
            return created(Json.toJson(inserted));
        } catch (PersistenceException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(409,e.getMessage());
            return badRequest(errorHandler.getMessage());
        }
    }

    public static Result updateAlbum(Long id) {
        Album album = Json.fromJson(request().body().asJson(), Album.class);
        try {
            Album updated = Album.updateAlbum(id, album);
            return ok(Json.toJson(updated));
        } catch (PersistenceException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(409,e.getMessage());
            return badRequest(errorHandler.getMessage());
        }
    }

    public static Result deleteAlbum(Long id) {
        Album.deleteAlbum(id);
        return noContent();
    }

}
