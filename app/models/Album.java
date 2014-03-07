package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album extends Model {

    @Id
    public Long id;

    @Required
    @Column(name= "album_name")
    public String albumName;

    public static Finder<Long, Album> find = new Finder(
            Long.class, Album.class
    );

    public static List<Album> getAlbums() {
        return find.all();
    }

    public static Album getAlbum(Long id) {
        return find.byId(id);
    }

    public static Album addAlbum(Album album) {
        album.save();
        return album;
    }

    public static Album updateAlbum(Long id, Album album) {
        album.id = id;
        album.update();
        return find.byId(id);
    }

    public static void deleteAlbum(Long id) {
        find.ref(id).delete();
    }

}
