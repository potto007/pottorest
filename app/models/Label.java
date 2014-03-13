package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name = "label")
public class Label extends Model {

    @Id
    @GeneratedValue
    @Column(name= "label_id")
    public Long id;

    @Required
    @Column(name= "label_name", unique = true)
    public String labelName;

    public Label(String labelName) {
        this.labelName = labelName;
    }

    public static Finder<Long, Label> find = new Finder(
            Long.class, Label.class
    );

    public static List<Label> getLabels() {
        return find.all();
    }

    public static Label getLabel(Long id) {
        return find.byId(id);
    }

    public static Label addLabel(Label label) {
        label.save();
        return label;
    }

    public static Label updateLabel(Long id, Label label) {
        label.id = id;
        label.update();
        return find.byId(id);
    }

    public static void deleteLabel(Long id) {
        find.ref(id).delete();
    }

}
