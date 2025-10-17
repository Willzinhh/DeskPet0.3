package br.cspi.service;

import br.cspi.infra.TratadorDeErros;
import br.cspi.model.usuario.DadosUser;
import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        List<DadosUser> users =repository.findUserByOwner(id).stream().map(DadosUser::new).toList();
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

    public void excluir(Long id) {
        this.repository.deleteById(id);
    }

    public void editar(User user) {
        User u = this.repository.getReferenceById(user.getId());
        u.setNome(user.getNome());
        u.setEmail(user.getEmail());
        u.setSenha(user.getSenha());
        u.setId(user.getId());

        if (this.repository.existsById(user.getId())) {}

        this.repository.save(u);
    }
}
