import models.*;

import org.junit.*;
import static org.junit.Assert.*;

import play.libs.Json;
import play.test.WithApplication;
import static play.test.Helpers.*;

import exceptions.*;

import javax.persistence.PersistenceException;

public class ModelsTest extends WithApplication {
    @Before
    public void setup() {
        start(fakeApplication(inMemoryDatabase()));
    }

    @Test
    public void createAndRetrieveLabel() {
        new Label("TestLabel").save();
        Label testLabel = Label.find.where().eq("label_name", "TestLabel").findUnique();
        assertNotNull(testLabel);
        assertEquals("TestLabel", testLabel.labelName);
    }

    @Test
    public void createAndRetrieveArtistBad() {
        try {
            Artist inserted = Artist.addArtist(new Artist("TestArtist"), null);
            Artist testArtist = Artist.find.where().eq("artist_name", "TestArtist").findUnique();
            assertNull(inserted);
            assertNull(testArtist);
        } catch (NullProvidedException e) {
            assertEquals("Attempted to add null label to artist", e.getMessage());
        } catch (PersistenceException e) {
        }
    }

    @Test
    public void createAndRetrieveArtistGood() {
        try {
            new Label("TestLabel").save();
            Label testLabel = Label.find.where().eq("label_name", "TestLabel").findUnique();
            Artist inserted = Artist.addArtist(new Artist("TestArtist"), testLabel.id);
            Artist testArtist = Artist.find.where().eq("artist_name", "TestArtist").findUnique();
            assertNotNull(inserted);
            assertNotNull(testArtist);
            assertEquals("TestArtist", testArtist.artistName);
        } catch (NullProvidedException e) {
            assertEquals("Attempted to add null label to artist", e.getMessage());
        } catch (PersistenceException e) {
        }
    }

    @Test
    public void createAndRetrieveAlbumBad() {
        try {
            Album inserted = Album.addAlbum(new Album("TestAlbum"), null);
            Album testAlbum = Album.find.where().eq("album_name", "TestAlbum").findUnique();
            assertNull(inserted);
            assertNull(testAlbum);
        } catch (NullProvidedException e) {
            assertEquals("Attempted to add null artist to album", e.getMessage());
        } catch (PersistenceException e) {
        }
    }

    @Test
    public void createAndRetrieveAlbumGood() {
        try {
            new Artist("TestArtist").save();
            Label testArtist = Label.find.where().eq("artist_name", "TestArtist").findUnique();
            Album inserted = Album.addAlbum(new Album("TestAlbum"), testArtist.id);
            Album testAlbum = Album.find.where().eq("album_name", "TestAlbum").findUnique();
            assertNotNull(inserted);
            assertNotNull(testAlbum);
            assertEquals("TestAlbum", testAlbum.albumName);
        } catch (NullProvidedException e) {
            assertEquals("Attempted to add null artist to album", e.getMessage());
        } catch (PersistenceException e) {
        }
    }

    @Test
    public void createAndRetrieveSongNoAlbumBad() {
        try {
            new Artist("TestArtist").save();
            Artist testArtist = Artist.find.where().eq("artist_name", "TestArtist").findUnique();
            Song inserted = Song.addSong(new Song("TestSong"), testArtist.id, null);
            Song testSong = Song.find.where().eq("song_name", "TestSong").findUnique();
            assertNull(inserted);
            assertNull(testSong);
        } catch (NullProvidedException e) {
            assertEquals("Attempted to add null artist or album to song", e.getMessage());
        } catch (PersistenceException e) {
        }
    }

    @Test
    public void createAndRetrieveSongNoArtistBad() {
        try {
            new Album("TestAlbum").save();
            Album testAlbum = Album.find.where().eq("album_name", "TestAlbum").findUnique();
            Song inserted = Song.addSong(new Song("TestSong"), null, testAlbum.id);
            Song testSong = Song.find.where().eq("song_name", "TestSong").findUnique();
            assertNull(inserted);
            assertNull(testSong);
        } catch (NullProvidedException e) {
            assertEquals("Attempted to add null artist or album to song", e.getMessage());
        } catch (PersistenceException e) {
        }
    }

    @Test
    public void createAndRetrieveSongNoArtistNoAlbumBad() {
        try {
            Song inserted = Song.addSong(new Song("TestSong"), null, null);
            Song testSong = Song.find.where().eq("song_name", "TestSong").findUnique();
            assertNull(inserted);
            assertNull(testSong);
        } catch (NullProvidedException e) {
            assertEquals("Attempted to add null artist or album to song", e.getMessage());
        } catch (PersistenceException e) {
        }
    }

    @Test
    public void createAndRetrieveSongGood() {
        try {
            new Artist("TestArtist").save();
            Artist testArtist = Artist.find.where().eq("artist_name", "TestArtist").findUnique();
            new Album("TestAlbum").save();
            Album testAlbum = Album.find.where().eq("album_name", "TestAlbum").findUnique();
            Song inserted = Song.addSong(new Song("TestSong"), testArtist.id, testAlbum.id);
            Song testSong = Song.find.where().eq("song_name", "TestSong").findUnique();
            assertNotNull(inserted);
            assertNotNull(testSong);
            assertEquals("TestSong", testSong.songName);
        } catch (NullProvidedException e) {
            assertEquals("Attempted to add null artist or album to song", e.getMessage());
        } catch (PersistenceException e) {
        }
    }
}
