package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "song",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"song_name", "artist_artist_id"})})
public class Song extends Model {

    @Id
    @GeneratedValue
    @Column(name= "song_id")
    public Long id;

    @Required
    @Column(name= "song_name")
    public String songName;

    @ManyToOne
//    @PrimaryKeyJoinColumn(name="song_id", referencedColumnName = "artist_id")
    public Artist artist;
//    @Required
//    @Column(name= "artist_id")
//    public Long artistId;

    @ManyToOne
//    @PrimaryKeyJoinColumn(name="song_id", referencedColumnName = "album_id")
    public Album album;
//    @Required
//    @Column(name= "album_id")
//    public Long albumId;

    public static Finder<Long, Song> find = new Finder(
            Long.class, Song.class
    );

    public static List<Song> getSongs() {
        return find.all();
    }

    public static Song getSong(Long id) {
        return find.byId(id);
    }

    public static List<Song> getArtistSongs(Long artistId) {
        return find.where().ieq("artist_artist_id", artistId.toString()).findList();
    }

    public static List<Song> getAlbumSongs(Long albumId) {
        return find.where().ieq("album_album_id", albumId.toString()).findList();
    }

    public static Song addSong(Song song) {
        song = addSong(song, null, null);
        return song;
    }

    public static Song addSong(Song song, Long artistId, Long albumId) {
        song.artist = Artist.find.ref(artistId);
        song.album = Album.find.ref(albumId);
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
