package br.cspi.service;

import br.cspi.model.agendamento.Agendamento;
import br.cspi.model.agendamento.AgendamentoRepository;
import br.cspi.model.agendamento.DadosAgendamento;
import br.cspi.model.agendamento.InputDadosAgendamento;
import br.cspi.model.cliente.ClienteRepository;
import br.cspi.model.funcionario.Funcionario;
import br.cspi.model.funcionario.FuncionarioRepository;
import br.cspi.model.pet.Pet;
import br.cspi.model.pet.PetRepository;
import br.cspi.model.servico.Servico;
import br.cspi.model.servico.ServicoRepository;
import br.cspi.model.usuario.Owner;
import br.cspi.model.usuario.OwnerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final OwnerRepository ownerRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;


    public Agendamento salvar(long owner_id, long funcionario_id, long pet_id, long servico_id, Agendamento agendamento) {

        if (ownerRepository.findOwnerById(owner_id) == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        } else if (funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, funcionario_id) == null) {
            throw new NoSuchElementException("Funcionario não encontrado");
        } else if (petRepository.findPetByOwnerAndId(owner_id, pet_id) == null) {
            throw new NoSuchElementException("Pet não encontrado");
        } else if (servicoRepository.findServicoByOwnerAndId(owner_id, servico_id) == null) {
            throw new NoSuchElementException("Servico não encontrado");
        }

        Owner owner = ownerRepository.findOwnerById(owner_id);
        Funcionario funcionario = funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, funcionario_id);
        Pet pet = petRepository.findPetByOwnerAndId(owner_id, pet_id);
        Servico servico = servicoRepository.findServicoByOwnerAndId(owner_id, servico_id);

        agendamento.setOwner(owner);
        agendamento.setFuncionario(funcionario);
        agendamento.setPet(pet);
        agendamento.setServico(servico);


        return agendamentoRepository.save(agendamento);
    }

    public DadosAgendamento atualizar(long owner_id, Agendamento agendamento, long funcionario_id, long pet_id, long servico_id) {
        Agendamento la = agendamentoRepository.findAgendamentoByOwnerAndId(owner_id, agendamento.getId());
        System.out.println(pet_id+""+la.getPet().getId());
        if (la == null) {
            throw new NoSuchElementException("Agendamento não encontrado");
        }

        if ( servico_id == la.getFuncionario().getId()){
            agendamento.setFuncionario(la.getFuncionario());
        }
        else {
           Funcionario f = funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, funcionario_id);
           agendamento.setFuncionario(f);
        }

        if (pet_id == la.getPet().getId()) {
            agendamento.setPet(la.getPet());
        }
        else {
            Pet p = petRepository.findPetByOwnerAndId(owner_id, pet_id);
            agendamento.setPet(p);
        }
        if ( servico_id == la.getServico().getId()) {
            agendamento.setServico(la.getServico());
        }
        else {
            Servico s = servicoRepository.findServicoByOwnerAndId(owner_id, servico_id);
            agendamento.setServico(s);
        }
        agendamento.setOwner(la.getOwner());

        return new DadosAgendamento(agendamentoRepository.save(agendamento));
    }

    public void excluir(long owner_id, long id) {
        Agendamento a = agendamentoRepository.findAgendamentoByOwnerAndId(owner_id, id);
        System.out.println(a);
        if (a == null) {
            throw new NoSuchElementException("Agendamento não encontrado");
        }
        this.agendamentoRepository.delete(a);

    }

    public List<DadosAgendamento> listar(long owner_id) {
        List<DadosAgendamento> da = agendamentoRepository.findAgendamentosByOwner(owner_id).stream().map(DadosAgendamento::new).toList();
        if (da.isEmpty()) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        return da;
    }

    public DadosAgendamento getById(long owner_id, long id) {
        Agendamento a = agendamentoRepository.findAgendamentoByOwnerAndId(owner_id, id);
        if (a == null) {
            throw new NoSuchElementException("Agendamento não encontrado");
        }
        return new DadosAgendamento(a);
    }

}
