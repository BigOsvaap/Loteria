package viewmodel.history;

import model.Partida;
import model.mongoDB.HistoryModel;

import java.util.List;

public class HistoryViewModel {

    private HistoryModel historyModel;

    public HistoryViewModel(HistoryModel historyModel){
        this.historyModel = historyModel;
        historyModel.loadHistory();
    }

    public List<Partida> partidas(){
        return historyModel.getPartidas();
    }

}
