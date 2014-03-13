package models;

import java.util.*;

import exceptions.NullProvidedException;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "album")
public class Album extends Model {

    @Id
    @GeneratedValue
    @Column(name = "album_id")
    public Long id;

    @Required
    @Column(name= "album_name")
    public String albumName;

    @Required
    @ManyToOne
    public Artist artist;

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public static Finder<Long, Album> find = new Finder(
            Long.class, Album.class
    );

    public static List<Album> getAlbums() {
        return find.all();
    }

    public static List<Album> getArtistAlbums(Long artistId) {
        return find.where().ieq("artist_artist_id", artistId.toString()).findList();
    }

    public static Album getAlbum(Long id) {
        return find.byId(id);
    }

    public static Album addAlbum(Album album, Long artistId) throws NullProvidedException {
        if (artistId == null) {
            throw new NullProvidedException("Attempted to add null artist to album");
        }
        album.artist = Artist.find.ref(artistId);
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
