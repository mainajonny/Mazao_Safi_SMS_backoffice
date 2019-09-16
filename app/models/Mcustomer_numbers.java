package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by maina on 9/11/2019.
 */
@Entity
public class Mcustomer_numbers extends Model {
    @Id
    public long cid;

    @Constraints.Required
    public String Tel;

    @Constraints.Required
    public String Type;

    public static Model.Finder<Long, Mcustomer_numbers> findCustomernumbers = new Model.Finder<Long, Mcustomer_numbers>(Long.class, Mcustomer_numbers.class);

    public static List<Mcustomer_numbers> findAll(){
        return findCustomernumbers.all();
    }

    public static List<Mcustomer_numbers> findByType(String Type){
        return findCustomernumbers.where().eq("Type", Type).findList();
    }

}
