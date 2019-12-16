package controller;

import datamodel.VekilNesnesi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {
    private List<VekilNesnesi> vekiller;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private Button yeniKayitBtn;

    @FXML
    private ListView vekillerListView;

    public void initialize(){

        VekilNesnesi vekil1 = new VekilNesnesi("Marc", "Ayrım","Salih",
                LocalDate.of(1960,3,01));
        VekilNesnesi vekil2 = new VekilNesnesi("Delori", "Hasköy","Yağmur",
                LocalDate.of(1990,10,22));
        VekilNesnesi vekil3 = new VekilNesnesi("Nusrettin", "Delil","Meral",
                LocalDate.of(1990,10,20));
        VekilNesnesi vekil4 = new VekilNesnesi("Hayrettin", "Şems","Mel",
                LocalDate.of(1997,9,27));

        vekiller = new ArrayList<VekilNesnesi>();
        vekiller.add(vekil1);
        vekiller.add(vekil2);
        vekiller.add(vekil3);
        vekiller.add(vekil4);

        vekillerListView.getItems().setAll(vekiller);
        vekillerListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void handleClickListView() {
        VekilNesnesi vekil = (VekilNesnesi) vekillerListView.getSelectionModel().getSelectedItem();
        System.out.println("Selected vekil info is : " + vekil);
    }

    @FXML
    public void yeniKayitcontroller(){
        Dialog<ButtonType> dialog =  new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setHeight(800);

        try{
            Parent root = FXMLLoader.load(getClass().getResource("/resources/views/yeniKayitForm.fxml"));
            dialog.getDialogPane().setContent(root);


        } catch (IOException e) {
            System.out.println("Dialog yüklenemedi");
            e.printStackTrace();
        }
        ButtonType ok = new ButtonType("Kaydet",ButtonBar.ButtonData.OK_DONE);
        ButtonType uygula = new ButtonType("Uygula",ButtonBar.ButtonData.APPLY);
        ButtonType vazgec = new ButtonType("Vazgeç",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(ok);
        dialog.getDialogPane().getButtonTypes().add(vazgec);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.get().getButtonData() == ButtonBar.ButtonData.OK_DONE) {
            System.out.println("kaydet");
        } else if (result.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
            System.out.println("vazgeç");
        } else if (result.get().getButtonData() == ButtonBar.ButtonData.APPLY) {
            System.out.println("Değişiklikler algılandı");
        }



    }
}
