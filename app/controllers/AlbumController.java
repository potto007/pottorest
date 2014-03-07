package controllers;

import java.util.*;

import play.mvc.*;
import play.libs.Json;

import models.*;

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
        Album inserted = Album.addAlbum(newAlbum);
        return created(Json.toJson(inserted));
    }

    public static Result updateAlbum(Long id) {
        Album album = Json.fromJson(request().body().asJson(), Album.class);
        Album updated = Album.updateAlbum(id, album);
        return ok(Json.toJson(updated));
    }

    public static Result deleteAlbum(Long id) {
        Album.deleteAlbum(id);
        return noContent();
    }

}
