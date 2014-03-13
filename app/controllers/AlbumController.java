package controllers;

import java.util.*;

import exceptions.*;
import play.mvc.*;
import play.libs.Json;

import models.*;

import javax.persistence.PersistenceException;

public class AlbumController extends Controller{

    public static Result getAlbums() {
        List<Album> albums = Album.getAlbums();
        return ok(Json.toJson(albums));
    }

// This should be an overloaded form of getAlbum, but static binding of routes prevents that from being allowed.
    public static Result getArtistAlbum(Long artistId, Long albumId) {
        return getAlbum(albumId);
    }

    public static Result getAlbum(Long albumId) {
        Album album = Album.getAlbum(albumId);
        return album == null ? notFound() : ok(Json.toJson(album));
    }

    public static Result getArtistAlbums(Long artistId) {
        List<Album> albums = Album.getArtistAlbums(artistId);
        return ok(Json.toJson(albums));
    }

    public static Result createAlbum(Long artistId) {
        Album newAlbum = Json.fromJson(request().body().asJson(), Album.class);
        try {
            Album inserted = Album.addAlbum(newAlbum, artistId);
            return created(Json.toJson(inserted));
        } catch (NullProvidedException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(400,e.getMessage());
            return badRequest(errorHandler.getMessage());
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
