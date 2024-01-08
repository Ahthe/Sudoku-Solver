module starsahtheshamali.sudokusolver {
    requires javafx.controls;
    requires javafx.fxml;


    opens starsahtheshamali.sudokusolver to javafx.fxml;
    exports starsahtheshamali.sudokusolver;
}