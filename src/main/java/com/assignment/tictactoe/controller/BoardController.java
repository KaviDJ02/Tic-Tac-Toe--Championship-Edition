package com.assignment.tictactoe.controller;

import com.assignment.tictactoe.service.BoardUi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BoardController implements BoardUi {

    @FXML
    private Button cell1;

    @FXML
    private Button cell2;

    @FXML
    private Button cell3;

    @FXML
    private Button cell4;

    @FXML
    private Button cell5;

    @FXML
    private Button cell6;

    @FXML
    private Button cell7;

    @FXML
    private Button cell8;

    @FXML
    private Button cell9;

    @FXML
    private AnchorPane gamePane;

    @FXML
    private Label msgLabel;

    @FXML
    private Label roundLabel;

    @FXML
    private ImageView oImage;

    @FXML
    private Label oScore;

    @FXML
    private AnchorPane welcomePane;

    @FXML
    private ImageView xImage;

    @FXML
    private Label xScore;

    @FXML
    void backButtonOnAction(ActionEvent event) {

    }

    @FXML
    void resetButtonOnAction(ActionEvent event) {

    }

    @FXML
    void nextButtonOnAction(ActionEvent event) {

    }

    @Override
    public void update(int row, int col, boolean isHuman) {

    }

    @Override
    public void notifyWinner() {

    }
}
