package br.cspi.service;

import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User salvar(User user) {this.repository.save(user);
        return user;
    }

    public List<User> listar() {
        System.out.println("oi");
        return this.repository.findAll();}

    public User getUser(Long id) {return this.repository.findById(id).get();}

    public void excluir(Long id) {this.repository.deleteById(id);}

    public void editar(User user) {
        User u = this.repository.getReferenceById(user.getId());
        u.setNome(user.getNome());
        u.setEmail(user.getEmail());
        u.setSenha(user.getSenha());
        u.setId(user.getId());

        this.repository.save(u);
    }
}
