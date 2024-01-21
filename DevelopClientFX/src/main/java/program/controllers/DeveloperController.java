package program.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import program.Main;
import program.models.Developer; // Изменен импорт
import program.utils.alerts.Alerts;
import program.utils.api.Api;
import program.utils.validation.Validation;

import java.util.List;

public class DeveloperController {
    private Main main;
    private Stage stage;
    protected Api api;
    private final ObservableList<Developer> developersData = FXCollections.observableArrayList(); //

    @FXML
    private TableView<Developer> developerTableView; //
    @FXML
    private TableColumn<Developer, String> developerCodeColumn; //
    @FXML
    private TableColumn<Developer, String> developerNameColumn; //
    @FXML
    private TableColumn<Developer, String> websiteColumn;
    @FXML
    private TableColumn<Developer, String> phoneColumn;
    @FXML
    private TableColumn<Developer, String> emailColumn;

    @FXML
    private TextField searchCompany;

    @FXML
    private CheckBox sortDeveloperBtn; //

    @FXML
    public void initialize() {
        searchCompany.setText(null);

        developerCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getCodeProperty()); //
        developerNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty()); //
        websiteColumn.setCellValueFactory(cellData -> cellData.getValue().getWebsiteProperty());
        phoneColumn.setCellValueFactory(cellData -> cellData.getValue().getPhoneNumberProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());

        developerTableView.setItems(developersData);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }


    @FXML
    public void handlerDevelopersBtn() {
        if (Validation.checkLength(searchCompany.getText(), 40)) {
            developerTableView.getItems().clear();
            boolean filter = sortDeveloperBtn.isSelected();
            List<Developer> result;
            if (Validation.isAirlineNameBlank(searchCompany.getText())) {
                result = api.getAllDevelopers(filter); //
            } else {
                result = api.getDevelopersBySubCompanyName(searchCompany.getText(), filter); //
            }
            developersData.addAll(result);
        } else {
            Alerts.showNoValidLength(stage, "поиск", 40);
        }
    }


    public void setMain(Main main) {
        this.main = main;
    }

    public void setApi(Api api) {
        this.api = api;
    }
}
