package view.history;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Partida;
import org.w3c.dom.CDATASection;
import view.Controller;
import view.ViewHandler;
import viewmodel.history.HistoryViewModel;

public class HistoryController implements Controller<HistoryViewModel> {

    @FXML
    private TableView<Partida> historyTable;
    @FXML
    private TableColumn<Partida,String> usernameColumn;
    @FXML
    private TableColumn<Partida,String> dateColumn;
    @FXML
    private TableColumn<Partida,String> hourColumn;
    @FXML
    private TableColumn<Partida,String> scoreColumn;

    private ObservableList<Partida> partidas;

    private ViewHandler viewHandler;
    private HistoryViewModel historyViewModel;

    @Override
    public void init(ViewHandler viewHandler, HistoryViewModel viewModel) {
        this.viewHandler = viewHandler;
        this.historyViewModel = viewModel;

        partidas = FXCollections.observableList(historyViewModel.partidas());

        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        hourColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("puntaje"));

        historyTable.setItems(partidas);

    }

    public void goMenu(){
        viewHandler.openView("Menu");
    }
}
