package br.com.quarkus.service;

import br.com.quarkus.domain.model.User;
import br.com.quarkus.domain.repository.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository repository;

    public List<User> findAll() {
        return repository.findAll().list();
    }

    public User findById(Long id) {
        User user = repository.findById(id);
        if (user == null)
            throw new NotFoundException("Usuário não encontrado pelo id");
        return user;
    }

    @Transactional
    public void save(User user) {
        repository.persist(user);
    }

    @Transactional
    public void deleteById(Long id) {
        User user = repository.findById(id);
        if (user == null)
            throw new NotFoundException("Usuário não encontrado pelo id");
        repository.delete(user);
    }
}
