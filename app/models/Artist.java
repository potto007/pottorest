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

    @ManyToOne
    @PrimaryKeyJoinColumn(name="artist_id", referencedColumnName = "label_id")
    private Label label;
//    @Required
//    @Column(name = "label_id")
//    public String labelId;

    public static Finder<Long, Artist> find = new Finder(
            Long.class, Artist.class
    );

    public static List<Artist> getArtists() {
        return find.all();
    }

    public static Artist getArtist(Long id) {
        return find.byId(id);
    }

    public static Artist addArtist(Artist artist) {
        artist.save();
        return artist;
    }

    public static Artist updateArtist(Long id, Artist artist) {
        artist.id = id;
        artist.update();
        return find.byId(id);
    }

    public static void deleteArtist(Long id) {
        find.ref(id).delete();
    }

}
