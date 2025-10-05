package br.cspi.service;

import br.cspi.model.servico.Servico;
import br.cspi.model.servico.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {this.servicoRepository = servicoRepository;}

    public List<Servico> listar() {return servicoRepository.findAll();}

    public Servico salvar(Servico servico) {return servicoRepository.save(servico);}

    public Servico alterar(Servico servico) {return servicoRepository.save(servico);}

    public Servico buscar(Long id) {return servicoRepository.findById(id).get();}

    public void excluir(Long id) {servicoRepository.deleteById(id);}



}
