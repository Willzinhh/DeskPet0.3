package br.cspi.service;

import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.OwnerRepository;
import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository repository;
    private final UserRepository userRepository;

    public OwnerService(OwnerRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Owner salvar(Owner owner) {
        this.repository.save(owner);


        return owner;
    }

    public Owner atualizar(Owner owner) {
        Owner lowner = getOwner(owner.getId());
        List<User> users = lowner.getUsers();
        owner.setUsers(users);
        owner.setUuid(lowner.getUuid());
        return repository.save(owner);
    }

    public List<Owner> listar() {
        return this.repository.findAll();
    }

    public Owner getOwner(Long id) {
        return this.repository.findById(id).get();
    }

    public void excluir(Long id) {
        Optional<Owner> owner = this.repository.findById(id);
        if(owner.isEmpty()) {
            throw new NoSuchElementException("Usuário não encontrado");
        }
        this.repository.deleteById(id);
    }

    public User atribuirUser(Long idOwner, User user) {
        Owner owner = this.repository.getReferenceById(idOwner);
        user.setSenha(new BCryptPasswordEncoder().encode(user.getSenha()));
        userRepository.save(user);
        owner.addUser(user);
        return user;
    }
}
