package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "song",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"song_name", "artist_id"})})
public class Song extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Required
    @Column(name= "song_name")
    public String songName;

    @Required
    @Column(name= "artist_id")
    public Long artistId;

    @Required
    @Column(name= "album_id")
    public Long albumId;

    public static Finder<Long, Song> find = new Finder(
            Long.class, Song.class
    );

    public static List<Song> getSongs() {
        return find.all();
    }

    public static Song getSong(Long id) {
        return find.byId(id);
    }

    public static Song addSong(Song song) {
        song.save();
        return song;
    }

    public static Song updateSong(Long id, Song song) {
        song.id = id;
        song.update();
        return find.byId(id);
    }

    public static void deleteSong(Long id) {
        find.ref(id).delete();
    }

}
