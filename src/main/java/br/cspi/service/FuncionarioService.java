package br.cspi.service;

import br.cspi.model.funcionario.DadosFuncionarioOutput;
import br.cspi.model.funcionario.Funcionario;
import br.cspi.model.funcionario.FuncionarioRepository;
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
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final OwnerRepository ownerRepository;
    private final ServicoRepository servicoRepository;


    public DadosFuncionarioOutput buscarPorId(long owner_id , long id) {
        Funcionario f = funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, id);
        if(f == null) {
            throw new NoSuchElementException("Funcionario não encontrado");
        }
        return new DadosFuncionarioOutput(f);

    }

    public List<DadosFuncionarioOutput> listar(long owner_id ) {
        List<DadosFuncionarioOutput> f = funcionarioRepository.findFuncionarioByOwner(owner_id).stream().map(DadosFuncionarioOutput::new).toList();
        if(f.isEmpty()) {
            throw new NoSuchElementException("Funcionarios não encontrado");
        }
        return f;
    }

    public DadosFuncionarioOutput salvar(long owner_id, Funcionario funcionario) {
        Owner owner = ownerRepository.findOwnerById(owner_id);;
        funcionario.setOwner(owner);
        if(owner == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }

        return  new DadosFuncionarioOutput(this.funcionarioRepository.save(funcionario));
    }

    public void excluir(long owner_id,long id) {
        Funcionario f = funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, id);
        if(f == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        this.funcionarioRepository.deleteById(id);
    }

    public DadosFuncionarioOutput editar(long owner_id, Funcionario funcionario) {
        Funcionario f = funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, funcionario.getId());
        if(f == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        else if(f.getServicos() != funcionario.getServicos() && funcionario.getServicos() == null) {
            funcionario.setServicos(f.getServicos());

        }

        funcionario.setOwner(f.getOwner());
        return new DadosFuncionarioOutput( funcionarioRepository.save(funcionario));
    }

    public void associarServico(Long owner_id, Long funcionario_id, Long servico_id) {
        Funcionario f = funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, funcionario_id);
        Servico s = servicoRepository.findServicoByOwnerAndId(owner_id, servico_id);

        if(f == null || s == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }

        f.addServico(s);
        funcionarioRepository.save(f); // Hibernate salva também na tabela intermediária
    }

    public List<DadosFuncionarioOutput> listarByServico(long owner_id, long servicoId) {
        List<DadosFuncionarioOutput> f = this.funcionarioRepository.findFuncionariosByServico(owner_id, servicoId).stream().map(DadosFuncionarioOutput::new).toList();
        if(f.isEmpty()) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        return f;

    }
}
