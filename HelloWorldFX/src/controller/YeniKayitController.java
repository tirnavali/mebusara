package controller;

import datamodel.DataSource;
import datamodel.Donem;
import datamodel.Meslek;
import datamodel.Parti;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class YeniKayitController {
    private int yeniAlanFlag=1; // parti, il, dönem için yeni alan eklerken sayma görevi yapar.
    private int yeniAlanRow = 9;
    private int controlListeBoyutu = 0;

    private List<Control> controlList = new ArrayList<Control>();// parti, il, dönem için eklenen satırı tutar.
    @FXML
    private GridPane yeniVeriGridPane;
    @FXML
    private Button yeniSecimBilgisiButton, meslekEkleButton;
    @FXML
    private ChoiceBox<Meslek> meslekChoiceBox;
    @FXML
    private ChoiceBox<Donem> donemBox;
    @FXML
    private ChoiceBox<Parti> partiBox;

    @FXML
    private AnchorPane yeniKayitPane;


    public void initialize(){
        meslekEkleButton.setTooltip(new Tooltip("Veritabanındaki meslekleri düzenlemek için tıklayın"));

        DataSource veritabani = DataSource.getInstance();
        //// get meslekler
        GetAllMesleklerTask2 getAllMesleklerTask = new GetAllMesleklerTask2();
        GetAllDonemlerTask getAllDonemlerTask = new GetAllDonemlerTask();

        meslekChoiceBox.setItems(getAllMesleklerTask.call());
        meslekChoiceBox.setTooltip(new Tooltip("Meslek seçiniz"));
        new Thread(getAllMesleklerTask).start();
        meslekChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                System.out.println("metin : "+ getAllMesleklerTask.call().get(t1.intValue())+ "  index: "+t1);
            }
        });

        //// get meslekler
        donemBox.setItems(getAllDonemlerTask.call());
        new Thread(getAllDonemlerTask).start();

        GetAllPartilerTask getAllPartilerTask = new GetAllPartilerTask();
        partiBox.setItems(getAllPartilerTask.call());
        new Thread(getAllPartilerTask).start();


        //meslekChoiceBox.getItems().setAll(denemeler);

//        ObservableList denek = null;
//        ArrayList arylist = new ArrayList();
//        arylist.add(0, "sdfsdfds");
//        denek.setAll(arylist);
//        meslekChoiceBox.setItems(denek);
//        for (Meslek meslek : denemeler) {
//            System.out.println(meslek.toString());
//        }

    }
    @FXML
    public void meslekEkleFormu(){ //meslekler tablosuna meslek ekler
        System.out.println("MESLEK EKLE FORMU FONKSİYONU -- "+getClass().toString());
        Dialog<ButtonType> meslekDialog = new Dialog<>();
        meslekDialog.initOwner(yeniKayitPane.getScene().getWindow());

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/views/meslekler1.fxml"));
            meslekDialog.getDialogPane().setContent(root);
        } catch (Exception e) {
            System.out.println("FXML SAYFASI YÜKLENEMEDİ");
            e.printStackTrace();

        }

        ButtonType ok = new ButtonType("Tamam",ButtonBar.ButtonData.OK_DONE);
        meslekDialog.getDialogPane().getButtonTypes().add(ok);
        Optional<ButtonType> result = meslekDialog.showAndWait();
        if(result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            System.out.println("kaydet");

            System.out.println("Meslek ekle formu geliyor...");
        }
    }

    @FXML
    public void yeniAlanEkle() {
        System.out.println("Yeni alan ekle");
        int flag =yeniAlanFlag;
//        String[] kutular = {"donem","parti","il","button"};
//
//        for (int i=0;i<kutular.length;i++) {
//            kutular[i] = kutular[i]+flag;
//            System.out.format("Kutu {%s} : {%s} \n",i,kutular[i]);
//
//            new ChoiceBox().setId(kutular);
//            yeniVeriGridPane.add();
//        }
        controlList.add(new ChoiceBox()); //dönem
        controlList.add(new ChoiceBox()); //parti
        controlList.add(new ChoiceBox()); //iller
        controlList.add(new Button()); //+ tuşu

        for(int i = controlListeBoyutu; i< controlList.size(); i++) {
            if(i%4 == 3) {
                Control btn = controlList.get(i);
                btn.setOnMouseClicked(new EventHandler<>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        yeniAlanEkle();
                    }
                });
            }
            yeniVeriGridPane.add(controlList.get(i),(i%4),yeniAlanRow); //
        }
        if(controlListeBoyutu >=3) { //her yeni satırda- yeni ekle tuşunu disable yapar
            Control eklemeTusu = controlList.get(controlListeBoyutu -1);
            eklemeTusu.setDisable(true);
        }
        controlListeBoyutu = controlList.size(); // 4 kontrol tuşu ekledikten sonra liste boyutunu kaydeder.
        yeniAlanFlag++;//Bu fonk tekrar çalıştırıldığında hata olmaması için bayrak sayısını arttırır.
        yeniAlanRow++;
        yeniSecimBilgisiButton.setDisable(true);// o satırdaki yeniden eklemeye son ver
    }


}

class GetAllMesleklerTask2 extends Task {
    @Override
    public ObservableList<Meslek> call() {
        return FXCollections.observableArrayList(DataSource.getInstance().queryMeslekler());
    }
}
class GetAllDonemlerTask extends Task {
    @Override
    public ObservableList<Donem> call() {
        return FXCollections.observableArrayList(DataSource.getInstance().queryDonemler());
    }
}
class GetAllPartilerTask extends Task{
    @Override
    public ObservableList<Parti> call(){
        return FXCollections.observableArrayList(DataSource.getInstance().queryPartiler());
    }
}

