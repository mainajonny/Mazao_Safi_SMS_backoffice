package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by maina on 9/10/2019.
 */
@Entity
public class Mfarmer_numbers extends Model {
    @Id
    public long nid;

    @Constraints.Required
    public String Tel;

    public static Model.Finder<Long, Mfarmer_numbers> findPhonenumbers = new Model.Finder<Long, Mfarmer_numbers>(Long.class, Mfarmer_numbers.class);

    public static List<Mfarmer_numbers> findAll(){
        return findPhonenumbers.all();
    }

}
