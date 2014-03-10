package controllers;

import java.util.*;

import exceptions.WebAppExceptionHandler;
import play.mvc.*;
import play.libs.Json;

import models.*;

import javax.persistence.PersistenceException;

public class LabelController extends Controller{

    public static Result getLabels() {
        List<Label> labels = Label.getLabels();
        return ok(Json.toJson(labels));
    }

    public static Result getLabel(Long id) {
        Label label = Label.getLabel(id);
        return label == null ? notFound() : ok(Json.toJson(label));
    }

    public static Result createLabel() {
        Label newLabel = Json.fromJson(request().body().asJson(), Label.class);
        try {
            Label inserted = Label.addLabel(newLabel);
            return created(Json.toJson(inserted));
        } catch (PersistenceException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(409,e.getMessage());
            return badRequest(errorHandler.getMessage());
        }
    }

    public static Result updateLabel(Long id) {
        Label label = Json.fromJson(request().body().asJson(), Label.class);
        try {
            Label updated = Label.updateLabel(id, label);
            return ok(Json.toJson(updated));
        } catch (PersistenceException e) {
            WebAppExceptionHandler errorHandler = new WebAppExceptionHandler(409,e.getMessage());
            return badRequest(errorHandler.getMessage());
        }
    }

    public static Result deleteLabel(Long id) {
        Label.deleteLabel(id);
        return noContent();
    }

}
