package seminar5_6;

import seminar5_6.controller.UserController;
import seminar5_6.model.User;
import seminar5_6.model.dao.impl.FileOperation;
import seminar5_6.model.repository.GBRepository;
import seminar5_6.model.repository.impl.UserRepository;
import seminar5_6.view.UserView;

import static seminar5_6.logger.Log.createLog;
import static seminar5_6.util.DBConnector.DB_PATH;
import static seminar5_6.util.DBConnector.createDB;

public class Main {
    public static void main(String[] args) {
        createDB();
        createLog();
        FileOperation fileOperation = new FileOperation(DB_PATH);
        GBRepository<User, Long> repository = new UserRepository(fileOperation);
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller);
        view.run();
    }

}