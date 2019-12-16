package datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringPropertyBase;

public class Meslek {
    private SimpleIntegerProperty id;
    private SimpleStringProperty meslek_adi;

    public Meslek() {
        this.id = new SimpleIntegerProperty();
        this.meslek_adi = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getMeslek_adi() {
        return meslek_adi.get();
    }

    public void setMeslek_adi(String meslek_adi) {
        this.meslek_adi.set(meslek_adi);
    }

    @Override
    public String toString() {
        return meslek_adi.get();
    }
}
