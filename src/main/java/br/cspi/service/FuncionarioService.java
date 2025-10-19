package br.cspi.service;

import br.cspi.model.funcionario.DadosFuncionario;
import br.cspi.model.funcionario.Funcionario;
import br.cspi.model.funcionario.FuncionarioRepository;
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


    public DadosFuncionario buscarPorId(long owner_id ,long id) {
        Funcionario f = funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, id);
        if(f == null) {
            throw new NoSuchElementException("Funcionario não encontrado");
        }
        return new DadosFuncionario(f);

    }

    public List<DadosFuncionario> listar(long owner_id ) {
        List<DadosFuncionario> f = funcionarioRepository.findFuncionarioByOwner(owner_id).stream().map(DadosFuncionario::new).toList();
        if(f.isEmpty()) {
            throw new NoSuchElementException("Funcionarios não encontrado");
        }
        return f;
    }

    public DadosFuncionario salvar(long owner_id, Funcionario funcionario) {
        Owner owner = ownerRepository.findOwnerById(owner_id);;
        funcionario.setOwner(owner);
        if(owner == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }

        return  new DadosFuncionario(this.funcionarioRepository.save(funcionario));
    }

    public void excluir(long owner_id,long id) {
        Funcionario f = funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, id);
        if(f == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        this.funcionarioRepository.deleteById(id);
    }

    public DadosFuncionario editar(long owner_id, Funcionario funcionario) {
        Funcionario f = funcionarioRepository.findFuncionarioByOwnerAndId(owner_id, funcionario.getId());
        if(f == null) {
            throw new NoSuchElementException("Proprietario não encontrado");
        }
        else if(f.getServicos() != funcionario.getServicos() && funcionario.getServicos() == null) {
            funcionario.setServicos(f.getServicos());

        }

        funcionario.setOwner(f.getOwner());
        return new DadosFuncionario( funcionarioRepository.save(funcionario));
    }
}
