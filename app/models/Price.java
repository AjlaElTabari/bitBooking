package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.Form;
import play.data.format.Formats;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Alen Bumbulovic on 9/17/2015.
 */
@Entity
public class Price extends Model {
    public static final Form<Price> priceForm = Form.form(Price.class);
    public static Finder<String, Price> finder = new Finder<String, Price>(Price.class);

    @Id
    public Integer id;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    @Column(columnDefinition = "datetime")
    public Date checkIn;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    @Column(columnDefinition = "datetime")
    public Date checkOut;

    public BigDecimal cost;

    @ManyToOne
    public Room room;


    public Price(Integer id, Date checkIn, Date checkOut, BigDecimal cost, Room room) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cost = cost;
        this.room = room;
    }

    public static Price findPriceById(Integer id) {
        Price price = finder.where().eq("id", id).findUnique();

        return price;
    }

    public String toString() {
        return checkIn + " " + checkOut + " " + cost.toString();
    }

}