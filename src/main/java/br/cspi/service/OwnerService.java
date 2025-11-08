package br.cspi.service;

import br.cspi.model.usuario.*;
import jakarta.validation.Valid;
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

    public Owner salvar(@Valid DadosOwnerInput ownerInput) {

        Owner owner = new Owner();
        owner.setNome(ownerInput.nome());
        owner.setCpf(ownerInput.cpf());
        owner.setCnpj(ownerInput.cnpj());
        owner.setTelefone(ownerInput.telefone());
        owner.setEndereco(ownerInput.endereco());
        owner.setNome_empresa(ownerInput.nome_empresa());
        owner.setPlano(ownerInput.plano());
        this.repository.save(owner);

        owner = repository.findByCpf(ownerInput.cpf());

        return owner;
    }

    public Owner atualizar(Owner owner) {
        Owner lowner = repository.findById(owner.getId()).orElseThrow(NoSuchElementException::new);
        List<User> users = lowner.getUsers();
        owner.setUsers(users);
        owner.setUuid(lowner.getUuid());
        return repository.save(owner);
    }

    public List<DadosOwnerOutput> listar() {
        return this.repository.findAll().stream().map(DadosOwnerOutput::new).toList();
    }

    public Optional<DadosOwnerOutput> getOwner(Long id) {
        return this.repository.findById(id).map(DadosOwnerOutput::new);
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
