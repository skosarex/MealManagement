package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        log.info("save {}", user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        return repository.computeIfPresent(user.getId(), (id, oldUser) -> user);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        List<User> userList = new ArrayList<>(repository.values());
        userList.sort(Comparator.comparing(AbstractNamedEntity::getName));
        return userList;
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream().filter(u -> u.getEmail().equals(email)).findAny().orElse(null);
    }

    // test
    public static void main(String[] args) {
        InMemoryUserRepository repo = new InMemoryUserRepository();
        repo.save(new User(null, "Teddy", "email1", "pass1", Role.ROLE_USER));
        repo.save(new User(null, "William", "email2", "pass2", Role.ROLE_USER));
        repo.save(new User(null, "Dora", "email3", "pass3", Role.ROLE_USER));
        repo.save(new User(null, "August", "email4", "pass4", Role.ROLE_USER));
        repo.save(new User(null, "Dora", "email5", "pass5", Role.ROLE_USER));

        System.out.println("___________");
        repo.getAll().forEach(System.out::println);
        System.out.println("___________");
        System.out.println(repo.getByEmail("email3"));
        System.out.println("___________");
    }
}
