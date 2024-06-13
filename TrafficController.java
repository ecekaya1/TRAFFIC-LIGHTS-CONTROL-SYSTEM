package com.example.proje;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Stack;


public class TrafficController {
    @FXML
    private TextField arac;
    @FXML
    private TextField yaya;
    @FXML
    private TableView<OncelikliArac> oncelikliAracTablo;
    @FXML
    private TableColumn<OncelikliArac, String> oncelik;
    @FXML
    private Circle aracIsik, yayaIsik;
    @FXML
    private ListView<Arac> gecen;
    @FXML
    private ListView<Arac> bekleyen;
    @FXML
    private ListView<Yaya> bekleyenYayaList;
    @FXML
    private ListView<Yaya> gecenYayaList;

    private BagliListe<Arac> bekleyenAraclar = new BagliListe<>();
    private Kuyruk<Arac> gecenAraclar = new Kuyruk<>();
    private BagliListe<Yaya> bekleyenYayalar = new BagliListe<>();
    private Stack<Yaya> gecenYayalar = new Stack<>();

    private void refresh() {
        ObservableList<Arac> bekleyenAraclarObservable = FXCollections.observableArrayList();
        for (int i = 0; i < bekleyenAraclar.size(); i++) {
            bekleyenAraclarObservable.add(bekleyenAraclar.get(i));
        }
        bekleyen.setItems(bekleyenAraclarObservable);

        ObservableList<Arac> gecenAraclarObservable = FXCollections.observableArrayList(gecenAraclar.toList());
        gecen.setItems(gecenAraclarObservable);

        ObservableList<Yaya> gecenYayalarObservable = FXCollections.observableArrayList(gecenYayalar);
        gecenYayaList.setItems(gecenYayalarObservable);

        ObservableList<Yaya> bekleyenYayalarObservable = FXCollections.observableArrayList();
        for (int i = 0; i < bekleyenYayalar.size(); i++) {
            bekleyenYayalarObservable.add(bekleyenYayalar.get(i));
        }
        bekleyenYayaList.setItems(bekleyenYayalarObservable);
    }

    @FXML
    protected void aracEkle() {
        String aracPlaka = arac.getText();
        if (!aracPlaka.isEmpty()) {
            Arac yeniArac = new Arac(aracPlaka);
            bekleyenAraclar.add(yeniArac);  // CustomLinkedList'e ekleme
        }
        refresh();
        arac.clear();
    }

    @FXML
    protected void aracCikar(int kacArac) {
        for (int i = 0; i < kacArac && !bekleyenAraclar.isEmpty(); i++) {
            Arac gecenArac = bekleyenAraclar.remove(); // CustomLinkedList'ten araç çıkar
            if (gecenArac != null) {
                gecenAraclar.add(gecenArac); // Kuyruğa ekle
            }
        }
        refresh();
    }

    @FXML
    protected void yayaEkle() {
        String yayaText = yaya.getText();
        if (!yayaText.isEmpty()) {
            Yaya yeniYaya = new Yaya(yayaText);
            bekleyenYayalar.add(yeniYaya);
            siralaVeEkle();
            refresh();
        }
        yaya.clear();
    }
    @FXML
    private void yayaCikar() {
        if (!bekleyenYayalar.isEmpty()) {
            Yaya yaya = bekleyenYayalar.remove();
            if (yaya != null) {
                gecenYayalar.push(yaya); // Geçen yayaları da stack'te tutma
            }
        }
        refresh();
    }
    private void siralaVeEkle() {
        for (int i = 0; i < bekleyenYayalar.size(); i++) {
            int flag = 0;
            for (int j = 0; j < bekleyenYayalar.size() - 1; j++) {
                if (bekleyenYayalar.get(j).getYas() < bekleyenYayalar.get(j + 1).getYas()) {
                    Yaya temp = bekleyenYayalar.get(j);
                    bekleyenYayalar.set(j, bekleyenYayalar.get(j + 1));
                    bekleyenYayalar.set(j + 1, temp);
                    flag = 1;
                }
            }
            if (flag == 0) {
                return;
            }
        }
    }


    @FXML
    protected void isikButonKontrol() {
        if (aracIsik.getFill() == Color.RED) {
            aracIsik.setFill(Color.GREEN);
            yayaIsik.setFill(Color.RED);
            aracCikar(3);
        } else {
            aracIsik.setFill(Color.RED);
            yayaIsik.setFill(Color.GREEN);
            yayaCikar();
        }
    }

    @FXML
    protected void oncelikliAracGetir() {
        Stack<OncelikliArac> oncelikliAraclarStack = new Stack<>();
        oncelikliAraclarStack.push(new OncelikliArac("Ambulans"));
        oncelikliAraclarStack.push(new OncelikliArac("Polis"));
        oncelikliAraclarStack.push(new OncelikliArac("Itfaiye"));

        ObservableList<OncelikliArac> oncelikliAraclar = FXCollections.observableArrayList();
        while (!oncelikliAraclarStack.isEmpty()) {
            oncelikliAraclar.add(oncelikliAraclarStack.pop());
        }

        oncelikliAracTablo.setItems(oncelikliAraclar);

        oncelik.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaka()));
    }

    @FXML
    protected void oncelikliAracGecir() {
        ObservableList<OncelikliArac> oncelikliAraclar = oncelikliAracTablo.getItems();
        if (!oncelikliAraclar.isEmpty()) {
            OncelikliArac gecirilenArac = oncelikliAraclar.remove(0);
            // Handle the passed priority vehicle here
        }
    }
}

