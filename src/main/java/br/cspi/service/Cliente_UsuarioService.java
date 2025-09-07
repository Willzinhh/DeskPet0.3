package br.cspi.service;

import br.cspi.model.usuario.Cliente_Usuario;
import br.cspi.model.usuario.Cliente_UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cliente_UsuarioService {

    private final Cliente_UsuarioRepository repository;

    public Cliente_UsuarioService(Cliente_UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvar(Cliente_Usuario cliente) {this.repository.save(cliente);}
    public List<Cliente_Usuario> listar() {return this.repository.findAll();}
    public Cliente_Usuario getClienteUsuario(Long id) {return this.repository.findById(id).get();}
    public void excluir(Long id) {this.repository.deleteById(id);}

    public void editar(Cliente_Usuario cliente) {
        Cliente_Usuario c = this.repository.getReferenceById(cliente.getId());
        c.setNome(cliente.getNome());
        c.setCpf(cliente.getCpf());
        c.setCnpj(cliente.getCnpj());
        c.setTelefone(cliente.getTelefone());
        c.setEndereco(cliente.getEndereco());
        c.setNome_empresa( cliente.getNome_empresa());
        c.setPlano(cliente.getPlano());
        this.repository.save(c);
    }
}
