package br.cspi.service;

import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.OwnerRepository;
import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository repository;
    private final UserRepository userRepository;

    public OwnerService(OwnerRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public Owner salvar(Owner owner) {this.repository.save(owner);

        return owner;
    }
    public Owner atualizar(Owner owner) {
        Owner lowner = getOwner(owner.getId());
        List<User> users = lowner.getUsers();
        owner.setUsers(users);
        return repository.save(owner);
    }

    public List<Owner> listar() {return this.repository.findAll();}

    public Owner getOwner(Long id) {return this.repository.findById(id).get();}

    public void excluir(Long id) {this.repository.deleteById(id);}

    public String atribuirUser(Long idOwner, User user) {
        Owner owner = this.repository.getReferenceById(idOwner);
        userRepository.save(user);
        owner.addUser(user);
        return "Usuario atualizado com sucesso!";
    }
}
