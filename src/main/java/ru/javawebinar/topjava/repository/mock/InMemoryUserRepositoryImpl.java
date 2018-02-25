package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private List<User> repository = new CopyOnWriteArrayList<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        for (User user : repository) {
            if (user.getId() == id) {
                repository.remove(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.add(user);
        return user;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        repository.sort(Comparator.comparing(AbstractNamedEntity::getName));
        return repository;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.stream().filter(user -> user.getEmail() == email).findFirst().orElse(null);
    }
}
