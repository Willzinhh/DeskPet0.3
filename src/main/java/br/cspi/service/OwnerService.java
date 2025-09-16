package br.cspi.service;

import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.OwnerRepository;
import br.cspi.model.usuario.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {

    private final OwnerRepository repository;

    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public Owner salvar(Owner owner) {this.repository.save(owner);

        return owner;
    }

    public List<Owner> listar() {return this.repository.findAll();}

    public Owner getOwner(Long id) {return this.repository.findById(id).get();}

    public void excluir(Long id) {this.repository.deleteById(id);}

    public void editar(Owner owner) {
        Owner c = this.repository.getReferenceById(owner.getId());
        c.setNome(owner.getNome());
        c.setCpf(owner.getCpf());
        c.setCnpj(owner.getCnpj());
        c.setTelefone(owner.getTelefone());
        c.setEndereco(owner.getEndereco());
        c.setNome_empresa( owner.getNome_empresa());
        c.setPlano(owner.getPlano());
        this.repository.save(c);
    }

    public String atribuirUser(Long idOwner, User user) {
        Owner owner = this.repository.getReferenceById(idOwner);
        owner.addUser(user);
        return "Usuario atualizado com sucesso!";
    }
}
