package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "artist")
public class Artist extends Model {

    @Id
    @GeneratedValue
    @Column(name = "artist_id")
    public Long id;

    @Required
    @Column(name= "artist_name", unique = true)
    public String artistName;

    @Required
    @ManyToOne
    public Label label;

    public Artist(String artistName, Label label) {
        this.artistName = artistName;
        this.label = label;
    }

    public static Finder<Long, Artist> find = new Finder(
            Long.class, Artist.class
    );

    public static List<Artist> getArtists() {
        return find.all();
    }

    public static List<Artist> getLabelArtists(Long labelId) {
        return find.where().ieq("label_label_id", labelId.toString()).findList();
    }

    public static Artist getArtist(Long id) {
        return find.byId(id);
    }

    public static Artist addArtist(Artist artist, Long labelId) {
        artist.label = Label.find.ref(labelId);
        artist.save();
        return artist;
    }

    public static Artist updateArtistLabel(Long artistId, Long labelId) {
        Artist artist = find.byId(artistId);
        artist.label = Label.find.ref(labelId);
        artist.update();
//        return find.byId(artistId);
        return artist;
    }

    public static Artist updateArtist(Artist artist, Long id) {
        artist.id = id;
        artist.update();
//        return find.byId(id);
        return artist;
    }

    public static void deleteArtist(Long id) {
        find.ref(id).delete();
    }

}
