package domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Auto.findOne", query = "select a from Auto a where a.id = :id"),
        @NamedQuery(name = "Auto.getAll", query = "select a from Auto a")
}
)
@Table(name = "Auto")
public class Auto implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "auto_id")
    private Integer id;

    @NotNull(message = "Username cannot be null")
    @Column(name = "merk")
    private String Merk;

    @NotNull(message = "Username cannot be null")
    @Column(name = "model")
    private String Model;

    @Column(name = "kenteken")
    private String Kenteken;
    @Min(value = 0, message = "Price should not be less than 0")
    @Max(value = 1000000, message = "Price should not be greater than 1.000.000")
    @Column(name = "prijs")
    private double Prijs;

    @Column(name = "carroserie")
    private Carroserie carroserie;

    @Column(name = "bouwjaar")
    private int Bouwjaar;

    @Column(name = "huidigBod")
    private double HuidigBod;


    public Auto() {

    }
    public Auto(String merk, String model, String kenteken, double prijs, Carroserie carroserie, int bouwjaar){



    }

    public Integer getId() {
        return id;
    }
    public Auto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMerk() {
        return Merk;
    }
    public Auto setMerk(String merk) {
        this.Merk = merk;
        return this;

    }

    public String getModel() {
        return Model;
    }
    public Auto setModel(String model) {
        this.Model = model;
        return this;

    }

    public String getKenteken() {
        return Kenteken;
    }
    public Auto setKenteken(String kenteken) {
        this.Kenteken = kenteken;
        return this;

    }

    public double getPrijs() {
        return Prijs;
    }
    public Auto setPrijs(double prijs) {
        this.Prijs = prijs;
        return this;

    }

    public Carroserie getCarroserie(){return carroserie;}
    public Auto setCarroserie(Carroserie carroserie){
        this.carroserie = carroserie;
        return this;
    }


    @Override
    public String toString() {
        return id + " " + Merk + " " + Model + " " + Kenteken + " " + Prijs + " " + carroserie;
    }

    public int getBouwjaar() {
        return Bouwjaar;
    }

    public void setBouwjaar(int bouwjaar) {
        Bouwjaar = bouwjaar;
    }

    public double getHuidigBod() {
        return HuidigBod;
    }

    public void setHuidigBod(double huidigBod) {
        HuidigBod = huidigBod;
    }
}
