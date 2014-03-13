package models;

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

    public void createAndRetrieveLabel() {
        new Label("Test Label").save();
    }
}
