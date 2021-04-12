package view.menu;

import view.Controller;
import view.ViewHandler;

public class MenuController implements Controller {

    private ViewHandler viewHandler;

    @Override
    public void init(ViewHandler viewHandler, Object viewModel) {
        this.viewHandler = viewHandler;
    }

    public void newGame(){
        viewHandler.openView("Board");
    }

    public void gameHistory(){
        viewHandler.openView("History");
    }

    public void globalRanking(){
        System.out.println("Ranking global");
    }

    public void signOut(){
        System.out.println("CERRAR SESION");
        viewHandler.openView("LogIn");
    }

}
