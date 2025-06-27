module com.tvd.it2302quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    //requires lombok;
    requires java.sql;

    opens com.tvd.it2302quizapp to javafx.fxml;
    exports com.tvd.it2302quizapp;
}
