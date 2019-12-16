package datamodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Parti {
    private SimpleIntegerProperty id;
    private SimpleStringProperty parti_adi;

    public Parti(){
        this.id         = new SimpleIntegerProperty();
        this.parti_adi  = new SimpleStringProperty();
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

    public String getParti_adi() {
        return parti_adi.get();
    }

    public SimpleStringProperty parti_adiProperty() {
        return parti_adi;
    }

    public void setParti_adi(String parti_adi) {
        this.parti_adi.set(parti_adi);
    }

    public String toString(){
        return this.parti_adi.getValue();
    }
}
