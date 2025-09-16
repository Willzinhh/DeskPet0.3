package br.cspi.service;

import br.cspi.model.cliente.ClienteRepository;
import br.cspi.model.cliente.Clientes;
import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.OwnerRepository;
import br.cspi.model.usuario.User;
import br.cspi.model.usuario.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {


        private final ClienteRepository repository;


        public ClienteService(ClienteRepository repository) {
            this.repository = repository;
        }


        public Clientes salvar(Clientes cliente) {this.repository.save(cliente);

            return cliente;
        }

        public List<Clientes> listar() {return this.repository.findAll();}

        public Clientes getCliente(Long id) {return this.repository.findById(id).get();}

        public void excluir(Long id) {this.repository.deleteById(id);}

        public void editar(Clientes cliente) {
            Clientes c = this.repository.getReferenceById( cliente.getId());
            c.setNome(cliente.getNome());
            c.setCpf(cliente.getCpf());
            c.setTelefone(cliente.getTelefone());
            c.setEndereco(cliente.getEndereco());

            this.repository.save(c);
        }


    }


