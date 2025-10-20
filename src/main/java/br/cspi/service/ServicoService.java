package br.cspi.service;

import br.cspi.model.servico.DadosServico;
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


    public List<DadosServico> listar(long owner_id) {
        List<DadosServico> s = servicoRepository.findServicosByOwner(owner_id).stream().map(DadosServico::new).collect(Collectors.toList());
        if (s.isEmpty()) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        return s;
    }

    public DadosServico salvar(long owner_id, Servico servico) {
        Owner owner = ownerRepository.findOwnerById(owner_id);
        if (owner == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        servico.setOwner(owner);

        return new DadosServico(this.servicoRepository.save(servico));
    }

    public DadosServico editar(long owner_id,Servico servico) {
        Servico s = this.servicoRepository.findServicoByOwnerAndId(owner_id, servico.getId());
        if (s == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        servico.setOwner(s.getOwner());
        servico.setFuncionarios(s.getFuncionarios());
        return new DadosServico( servicoRepository.save(servico));
    }

    public DadosServico buscar(long owner_id, long id) {
       Servico s = servicoRepository.findServicoByOwnerAndId(owner_id, id);
       if (s == null) {
           throw new NoSuchElementException("Proprietario não encontrado");
       }
       return new DadosServico(s);
    }

    public void excluir(long owner_id, long id) {
        Servico s = this.servicoRepository.findServicoByOwnerAndId(owner_id, id);
        if (s == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        servicoRepository.deleteById(id);
    }


    public List<DadosServico> listarByFuncionario(long ownerId, long funcionarioId) {
        List<DadosServico> ds = this.servicoRepository.findServicosByFuncionario(ownerId, funcionarioId).stream().map(DadosServico::new).toList();
        if (ds.isEmpty()) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        return ds;
    }
}
