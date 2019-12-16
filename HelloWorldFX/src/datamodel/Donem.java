package datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Donem {
    private SimpleIntegerProperty id;
    private SimpleStringProperty donem_adi;
    private SimpleStringProperty donem_bas_t;
    private SimpleStringProperty donem_bit_t;

    public Donem() {
        this.id          = new SimpleIntegerProperty();
        this.donem_adi   = new SimpleStringProperty();
        this.donem_bas_t = new SimpleStringProperty();
        this.donem_bit_t = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getDonem_adi() {
        return donem_adi.get();
    }

    public SimpleStringProperty donem_adiProperty() {
        return donem_adi;
    }

    public void setDonem_adi(String donem_adi) {
        this.donem_adi.set(donem_adi);
    }

    public String getDonem_bas_t() {
        return donem_bas_t.get();
    }

    public SimpleStringProperty donem_bas_tProperty() {
        return donem_bas_t;
    }

    public void setDonem_bas_t(String donem_bas_t) {
        this.donem_bas_t.set(donem_bas_t);
    }

    public String getDonem_bit_t() {
        return donem_bit_t.get();
    }

    public SimpleStringProperty donem_bit_tProperty() {
        return donem_bit_t;
    }

    public void setDonem_bit_t(String donem_bit_t) {
        this.donem_bit_t.set(donem_bit_t);
    }

    @Override
    public String toString() {
        return donem_adi.getValue();
    }
}
