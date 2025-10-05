package br.cspi.service;

import br.cspi.model.agendamento.Agendamento;
import br.cspi.model.agendamento.AgendamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {this.agendamentoRepository = agendamentoRepository;}

    public Agendamento salvar(Agendamento agendamento) {return agendamentoRepository.save(agendamento);}
    public Agendamento atualizar(Agendamento agendamento) {return agendamentoRepository.save(agendamento);}
    public void excluir(Agendamento agendamento) {this.agendamentoRepository.delete(agendamento);}
    public List<Agendamento> listar() {return agendamentoRepository.findAll();}
    public Agendamento getById(int id) {return agendamentoRepository.getById(id);}

}
