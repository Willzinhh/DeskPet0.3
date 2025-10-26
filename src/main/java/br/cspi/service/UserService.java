package br.cspi.service;

import br.cspi.model.usuario.DadosUser;
import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public DadosUser salvar(User user) {
        user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
        this.repository.save(user);
        return new DadosUser(user);
    }

    public List<DadosUser> listar(Long id) {
        List<DadosUser> users = repository.findUserByOwner(id).stream().map(DadosUser::new).toList();
        if (users.isEmpty()) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        return users;
    }

    public DadosUser getUser(Long owner_id, Long id) throws Throwable {
        User user = repository.findUserByOwnerAndId(owner_id, id);
        if (user == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        return new DadosUser(user);
    }

    public void excluir(long owner_id ,long id) {
        User user = repository.findUserByOwnerAndId(owner_id, id);
        if (user == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }

        this.repository.deleteById(id);
    }

    public DadosUser editar(User user, long owner_id) {
        User u = this.repository.findUserByOwnerAndId(owner_id, user.getId());

        if (u == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        user.setOwner(u.getOwner());

        return new DadosUser(this.repository.save(user));
    }
}
