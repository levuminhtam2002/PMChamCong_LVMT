package hust.project.base.home;

import javafx.scene.layout.Pane;

public class HomeController {
    private static final HomeView home = HomeView.instance();
    private static HomeController ins;
    private HomeController(){

    }
    public static HomeController instance() {
        if(ins == null){
            ins = new HomeController();
        }
        return ins;
    }
    public void changeScreen(Pane body){
        if(body != null){
            home.setCurrentScreen(body);
            return;
        }
        home.setCurrentScreen();
    }
}
