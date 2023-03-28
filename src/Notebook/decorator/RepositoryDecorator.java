package Notebook.decorator;

import Notebook.decorator.logger.Log;
import Notebook.model.User;
import Notebook.model.repository.GBRepository;
import Notebook.model.repository.impl.FileOperation;
import Notebook.model.repository.impl.UserRepository;
import Notebook.util.mapper.impl.UserMapper;

import java.util.List;
import java.util.Optional;

public class RepositoryDecorator implements GBRepository {
    private UserRepository user;
    private Log log;



    public RepositoryDecorator(UserRepository userRepository, Log log) {
        this.user = userRepository;
        this.log = log;
    }

    public RepositoryDecorator(FileOperation fileOperation) {
    }

    @Override
    public List findAll() {
        log.writerLog("Команда показать всех");
        return user.findAll();
    }

    @Override
    public User create(Object u) {
        log.writerLog("Был добавлен новый контакт: ");
        User user1 = user.create((User) u);
        log.writerLog("Имя: " + user1.getFirstName() + " Фамилия: " + user1.getLastName() + " Телефон: " + user1.getPhone());
        return user1;
    }

    @Override
    public Optional findById(Object id) {
        return Optional.empty();
    }

    @Override
    public Optional update(Object userId, Object update) {
        log.writerLog("Контакт под номером: "+ (long)userId + " был изменён!");
        return user.update((long)userId,(User) update);
    }

    @Override
    public Optional<User> delete(Object id) {
        return Optional.empty();
    }
}
