package br.cspi.service;

import br.cspi.model.usuario.DadosUserInput;
import br.cspi.model.usuario.DadosUserOutput;
import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    public DadosUserOutput salvar(User user) {
        user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
        this.repository.save(user);
        return new DadosUserOutput(user);
    }

    public List<DadosUserOutput> listar(Long id) {
        List<DadosUserOutput> users = repository.findUserByOwner(id).stream().map(DadosUserOutput::new).toList();
        if (users.isEmpty()) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        return users;
    }

    public DadosUserOutput getUser(Long owner_id, Long id) throws Throwable {
        User user = repository.findUserByOwnerAndId(owner_id, id);
        if (user == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        return new DadosUserOutput(user);
    }

    public void excluir(long owner_id ,long id) {
        User user = repository.findUserByOwnerAndId(owner_id, id);
        if (user == null) {
            throw new NoSuchElementException("Usuário não encontrado");
        }

        this.repository.deleteById(id);
    }

    public DadosUserOutput editar(@Valid DadosUserInput userInput, long owner_id) {
        User u = this.repository.findUserByOwnerAndId(owner_id, userInput.id());

        u.setNome(userInput.nome());
        u.setEmail(userInput.email());
        u.setSenha(new BCryptPasswordEncoder().encode(userInput.senha()));
        u.setPermissao(userInput.permissao());


        if (u == null) {
            System.out.println("id = " + userInput.id());
            throw new NoSuchElementException("Usuário não encontrado");
        }


        return new DadosUserOutput(this.repository.save(u));
    }
}
