import controller.UserController;
import model.User;
import model.dao.impl.FileOperation;
import model.repository.GBRepository;
import model.repository.impl.UserRepository;
import view.UserView;

import static logger.Log.createLog;
import static util.DBConnector.DB_PATH;
import static util.DBConnector.createDB;

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