package controllers;

import java.util.*;

import exceptions.WebAppExceptionHandler;
import play.mvc.*;
import play.libs.Json;

import models.*;

import javax.persistence.PersistenceException;

public class ArtistController extends Controller{

    public static Result getArtists() {
        List<Artist> artists = Artist.getArtists();
        return ok(Json.toJson(artists));
    }

    public static Result getArtist(Long id) {
        Artist artist = Artist.getArtist(id);
        return artist == null ? notFound() : ok(Json.toJson(artist));
    }

    public static Result createArtist() {
        Artist newArtist = Json.fromJson(request().body().asJson(), Artist.class);
        try {
            Artist inserted = Artist.addArtist(newArtist);
            return created(Json.toJson(inserted));
        } catch (PersistenceException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(409,e.getMessage());
            return badRequest(errorHandler.getMessage());
        }
    }

    public static Result updateArtist(Long id) {
        Artist artist = Json.fromJson(request().body().asJson(), Artist.class);
        try {
            Artist updated = Artist.updateArtist(id, artist);
            return ok(Json.toJson(updated));
        } catch (PersistenceException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(409,e.getMessage());
            return badRequest(errorHandler.getMessage());
        }
    }

    public static Result deleteArtist(Long id) {
        Artist.deleteArtist(id);
        return noContent();
    }

}
