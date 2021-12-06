module com.example.trabalho_dss {
    requires javafx.controls;
    requires javafx.fxml;

    exports com;
    opens com to javafx.fxml;
    exports com.controllers;
    opens com.controllers to javafx.fxml;
    exports com.controllers.PedidosDeOrcamento;
    opens com.controllers.PedidosDeOrcamento to javafx.fxml;
    exports com.controllers.Clientes;
    opens com.controllers.Clientes to javafx.fxml;
    exports com.controllers.Equipamentos;
    opens com.controllers.Equipamentos to javafx.fxml;
    exports com.controllers.Orcamentos;
    opens com.controllers.Orcamentos to javafx.fxml;
}