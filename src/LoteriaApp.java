import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelFactory;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

public class LoteriaApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory modelFactory = new ModelFactory("walle","misantla96");
        ViewModelFactory viewModelFactory = new ViewModelFactory(modelFactory);
        ViewHandler viewHandler = new ViewHandler(viewModelFactory);
        viewHandler.start();
    }

}