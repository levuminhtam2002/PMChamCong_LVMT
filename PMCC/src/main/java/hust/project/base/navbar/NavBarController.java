package hust.project.base.navbar;

import hust.project.base.constants.Route;
import hust.project.base.dashboard.Dashboard;
import hust.project.base.home.HomeController;
import hust.project.base.modified.Model.ModifiedEntity;

import hust.project.base.modified.Model.ModifiedRepository;
import hust.project.base.summary_logs.SummaryDepartmentController;
import hust.project.base.summary_logs.SummaryDepartmentView;
import hust.project.base.modified.pendingModified.PendingModifiedView;
import hust.project.base.modified.pendingModified.PendingModifiedController;

public class NavBarController {
    private final NavbarView navbar = NavbarView.instance();
    public void init(){
        handleNavigation();
    }
    private static NavBarController ins;
    private NavBarController() {
    }
    public static NavBarController instance() {
        if(ins == null){
            ins = new NavBarController();
        }
        return ins;
    }
    private void handleNavigation(){
        INavbarAction navbarAction = new INavbarAction() {
            @Override
            public void navigate(Route name) {
                switch (name){
                    case HOME_SCREEN:
                        HomeController.instance().changeScreen(null);
                        System.out.println("navigated to Home!");
                        break;
                    case DASHBOARD_SCREEN:
                        HomeController.instance().changeScreen(Dashboard.instance());
                        System.out.println("navigated to Dashboard!");
                        break;
                    case SUMMARY_DEPARTMENT_SCREEN:
                        HomeController.instance().changeScreen(SummaryDepartmentView.instance());
                        SummaryDepartmentView summaryDepartmentView = new SummaryDepartmentView();
                        new SummaryDepartmentController(summaryDepartmentView);
                        HomeController.instance().changeScreen(summaryDepartmentView);
                        System.out.println("navigated to Summary Department!");
                        break;
                    case DETAIL_SCREEN:
                        //TODO: navigate to detail screen
                        System.out.println("navigated to Detail!");
                        break;
                    case MODIFIED_SCREEN:
                        ModifiedRepository repository = new ModifiedEntity ();
                        PendingModifiedView modifiedView = PendingModifiedView.instance(); // Use the instance method if it's a singleton
//                        new PendingModifiedController(modifiedView, repository);
                        PendingModifiedController pendingModifiedController = PendingModifiedController.getInstance(modifiedView, repository);
                        HomeController.instance().changeScreen(modifiedView);
                        System.out.println("Navigated to Modified!");
                        break;

                }
            }

        };
        navbar.setNavbarAction(navbarAction);
    }

}
