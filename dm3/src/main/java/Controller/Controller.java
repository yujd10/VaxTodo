package Controller;

import Model.Data;
import Model.User;

public class Controller {
    private User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Data[] parseData(String[] data) {
        return null;
    }

}
