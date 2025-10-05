package br.cspi.service;

import br.cspi.model.funcionario.Funcionario;
import br.cspi.model.funcionario.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Funcionario buscarPorId(Long id) {return funcionarioRepository.findById(id).get();}

    public List<Funcionario> listar() {return funcionarioRepository.findAll();}

    public Funcionario salvar(Funcionario funcionario) {return funcionarioRepository.save(funcionario);}

    public void excluir(Long id) {this.funcionarioRepository.deleteById(id);}

    public Funcionario editar(Funcionario funcionario) {return funcionarioRepository.save(funcionario);}
}
