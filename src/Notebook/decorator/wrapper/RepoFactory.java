package Notebook.decorator.wrapper;

import Notebook.decorator.RepositoryDecorator;
import Notebook.decorator.logger.Log;
import Notebook.model.repository.impl.FileOperation;
import Notebook.model.repository.impl.UserRepository;

public class RepoFactory {
    private final FileOperation fileOperation;

    public RepoFactory(FileOperation fileOperation) {
       this.fileOperation = fileOperation;
    }

    private RepositoryDecorator workWithLog(FileOperation a){
        return new RepositoryDecorator(new UserRepository(a),new Log());
    }
}
