package br.cspi.service;

import br.cspi.model.servico.DadosServicoOutput;
import br.cspi.model.servico.Servico;
import br.cspi.model.servico.ServicoRepository;
import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;
    private final OwnerRepository ownerRepository;


    public List<DadosServicoOutput> listar(long owner_id) {
        List<DadosServicoOutput> s = servicoRepository.findServicosByOwner(owner_id).stream().map(DadosServicoOutput::new).collect(Collectors.toList());
        if (s.isEmpty()) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        return s;
    }

    public DadosServicoOutput salvar(long owner_id, Servico servico) {
        Owner owner = ownerRepository.findOwnerById(owner_id);
        if (owner == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        servico.setOwner(owner);

        return new DadosServicoOutput(this.servicoRepository.save(servico));
    }

    public DadosServicoOutput editar(long owner_id, Servico servico) {
        Servico s = this.servicoRepository.findServicoByOwnerAndId(owner_id, servico.getId());
        if (s == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        servico.setOwner(s.getOwner());
        servico.setFuncionarios(s.getFuncionarios());
        return new DadosServicoOutput( servicoRepository.save(servico));
    }

    public DadosServicoOutput buscar(long owner_id, long id) {
       Servico s = servicoRepository.findServicoByOwnerAndId(owner_id, id);
       if (s == null) {
           throw new NoSuchElementException("Proprietario não encontrado");
       }
       return new DadosServicoOutput(s);
    }

    public void excluir(long owner_id, long id) {
        Servico s = this.servicoRepository.findServicoByOwnerAndId(owner_id, id);
        if (s == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        servicoRepository.deleteById(id);
    }


    public List<DadosServicoOutput> listarByFuncionario(long ownerId, long funcionarioId) {
        List<DadosServicoOutput> ds = this.servicoRepository.findServicosByFuncionario(ownerId, funcionarioId).stream().map(DadosServicoOutput::new).toList();
        if (ds.isEmpty()) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        return ds;
    }
}
