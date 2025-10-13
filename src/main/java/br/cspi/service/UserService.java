package br.cspi.service;

import br.cspi.model.usuario.DadosUser;
import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public DadosUser salvar(User user) {
        user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
        this.repository.save(user);
        return new DadosUser(user);
    }

    public List<DadosUser> listar() {
        return repository.findAll().stream().map(DadosUser::new).toList();
    }

    public DadosUser getUser(Long id) {
        User user = repository.getOne(id);
        return new DadosUser(user);
        }

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
