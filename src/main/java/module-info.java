module com.assignment.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.assignment.tictactoe to javafx.fxml;
    exports com.assignment.tictactoe;
}