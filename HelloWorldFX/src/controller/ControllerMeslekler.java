package controller;

import datamodel.DataSource;
import datamodel.Meslek;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.List;

public class ControllerMeslekler {
    private DataSource dataSource;
    private List<Meslek> meslekler;
    private ObservableList<Meslek> meslekObservableList;

    @FXML
    private AnchorPane mesleklerPane;

    @FXML
    private ListView listPane;

    @FXML
    private TextField veriGirisi;


    public void initialize(){
        System.out.println("Meslek controller çalıştı");

        GetAllMesleklerTask task = new GetAllMesleklerTask();
        //listPane.setItems(task.call());
        listPane.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();


//        dataSource = DataSource.getInstance();
////        dataSource.open();
//        meslekler = dataSource.queryMeslekler();
//
//        meslekObservableList = FXCollections.observableList(meslekler);
//
//        meslekObservableList.addListener(new ListChangeListener<Meslek>() {
//            @Override
//            public void onChanged(Change<? extends Meslek> change) {
//                System.out.println("Şu anda  liste değişti");
//                listPane.getItems().addAll(meslekler);
//            }
//        });
//        listPane.getItems().addAll(meslekler);
    }

    public void meslekEkle(){
        String eklenecekMeslek = veriGirisi.getText();
        System.out.println("Eklenecek meslek : "+ eklenecekMeslek);
        if(!veriGirisi.getText().isEmpty()){
            try {
                //meslek ekleniyor
                DataSource.getInstance().addMeslek(eklenecekMeslek);
//                meslekler.clear();
                meslekler = DataSource.getInstance().queryMeslekler();
                listPane.getItems().setAll(meslekler);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Meslek eklenemedi");
            }

        } else {
            //kullanıcıya bir uyarı ver ekranda
            System.out.println("Meslek alanı boş");
        }
    }
}

class GetAllMesleklerTask extends Task {

    @Override
    public ObservableList<Meslek> call() {
        return FXCollections.observableArrayList(DataSource.getInstance().queryMeslekler());

    }
}
