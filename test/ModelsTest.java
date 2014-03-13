import models.*;

import org.junit.*;
import static org.junit.Assert.*;

import play.test.WithApplication;
import static play.test.Helpers.*;

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
        new Artist("TestArtist", null).save();
        Artist testArtist = Artist.find.where().eq("artist_name", "TestArtist").findUnique();
        assertNull(testArtist);
    }

    @Test
    public void createAndRetrieveArtistGood() {
        new Artist("TestArtist", Label.find.where().eq("label_name", "TestLabel").findUnique()).save();
        Artist testArtist = Artist.find.where().eq("artist_name", "TestArtist").findUnique();
        assertNotNull(testArtist);
        assertEquals("TestArtist", testArtist.artistName);
    }
}
