package Notebook;

import Notebook.controller.UserController;
import Notebook.decorator.RepositoryDecorator;
import Notebook.decorator.wrapper.RepoFactory;
import Notebook.model.User;
import Notebook.model.repository.impl.FileOperation;
import Notebook.model.repository.GBRepository;
import Notebook.model.repository.impl.UserRepository;
import Notebook.view.UserView;

import static Notebook.util.DBConnector.DB_PATH;
import static Notebook.util.DBConnector.createDB;

public class Main {
    public static void main(String[] args) {
        createDB();
        FileOperation fileOperation = new FileOperation(DB_PATH);
        GBRepository<User, Long> repository = new UserRepository(fileOperation);
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller);
        view.run();
    }

}