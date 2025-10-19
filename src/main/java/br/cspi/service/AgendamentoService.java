package br.cspi.service;

import br.cspi.model.agendamento.Agendamento;
import br.cspi.model.agendamento.AgendamentoRepository;
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

@Service
@AllArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final OwnerRepository ownerRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;



    public Agendamento salvar(long owner_id, long funcionario_id, long pet_id, long servico_id, Agendamento agendamento) {
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAA");

        if(ownerRepository.findOwnerById(owner_id) == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        } else if ( funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, funcionario_id) == null) {
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

    public Agendamento atualizar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public void excluir(Agendamento agendamento) {
        this.agendamentoRepository.delete(agendamento);
    }

    public List<Agendamento> listar() {
        return agendamentoRepository.findAll();
    }

    public Agendamento getById(int id) {
        return agendamentoRepository.getById(id);
    }

}
