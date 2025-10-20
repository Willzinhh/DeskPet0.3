package br.cspi.controller;

import br.cspi.model.agendamento.Agendamento;
import br.cspi.model.agendamento.AgendamentoRepository;
import br.cspi.model.agendamento.DadosAgendamento;
import br.cspi.model.agendamento.InputDadosAgendamento;
import br.cspi.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
@AllArgsConstructor
@Tag(name = "Agendamento", description = "Endpoints para gerenciamento de Agendamentos (Consultas, Serviços) dos Pets.")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;



    @GetMapping("/listar/{owner_id}")
    @Operation(summary = "Listar Agendamentos", description = "Lista todos os agendamentos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Agendamentos retornada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Agendamento.class))),
    })
    public List<DadosAgendamento> listar(@Parameter(description = "ID do Proprietario") @PathVariable int owner_id) {
        return agendamentoService.listar(owner_id); //
    }

    @GetMapping("/{owner_id}/{id}")
    @Operation(summary = "Buscar Agendamento por ID", description = "Retorna um agendamento específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.", content = @Content)
    })
    public DadosAgendamento buscar(@Parameter(description = "ID do Proprietario") @PathVariable int owner_id, @Parameter(description = "ID do Agendamento a ser buscado") @PathVariable int id) {
        return this.agendamentoService.getById(owner_id, id); //
    }

    @Operation(summary = "Criar novo Agendamento", description = "Cria e salva um novo agendamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos.", content = @Content)
    })
    @PostMapping({"{owner_id}/{funcionario_id}/{pet_id}/{servico_id}", ""})
    public Agendamento salvar(@Parameter(description = "ID do Proprietario") @PathVariable int owner_id, @Parameter(description = "ID do Funcionario") @PathVariable int funcionario_id, @Parameter(description = "ID do Pet") @PathVariable int pet_id, @Parameter(description = "ID do Servico") @PathVariable int servico_id, @RequestBody @Valid InputDadosAgendamento agendamento) {
        Agendamento agendamentoEntity = new Agendamento();
        agendamentoEntity.setData(agendamento.data());
        agendamentoEntity.setHorario(agendamento.horario());
        agendamentoEntity.setObservacao(agendamento.observacao());
        agendamentoEntity.setStatus(agendamento.status());
        agendamentoEntity.setPagamento(agendamento.pagamento());
        return this.agendamentoService.salvar(owner_id, funcionario_id, pet_id, servico_id, agendamentoEntity); //
    }

    @PutMapping("{owner_id}")
    @Operation(summary = "Atualizar Agendamento", description = "Atualiza os dados de um agendamento existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento atualizado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios", content = @Content),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado", content = @Content)

    })
    public DadosAgendamento atualizar(@Parameter(description = "ID do Proprietario") @PathVariable int owner_id, @RequestBody @Valid InputDadosAgendamento agendamento, UriComponentsBuilder uriBuilder) {
        Agendamento agendamentoEntity = new Agendamento();
        agendamentoEntity.setId(agendamento.id());
        agendamentoEntity.setData(agendamento.data());
        agendamentoEntity.setHorario(agendamento.horario());
        agendamentoEntity.setObservacao(agendamento.observacao());
        agendamentoEntity.setStatus(agendamento.status());
        agendamentoEntity.setPagamento(agendamento.pagamento());

        DadosAgendamento a = this.agendamentoService.atualizar(owner_id, agendamentoEntity, agendamento.funcionario_id(), agendamento.pet_id(), agendamento.servico_id()); //
        URI uri = uriBuilder.path("/agendamento/{agendamento_id}").buildAndExpand(a).toUri();
        return ResponseEntity.created(uri).body(a).getBody();
    }

    @DeleteMapping("/{owner_id}/{id}")
    @Operation(summary = "Deletar Agendamento", description = "Remove um agendamento pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento deletado com sucesso (No Content).", content = @Content),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Agendamento a ser deletado") @PathVariable int owner_id, @Parameter(description = "ID do Agendamento a ser deletado") @PathVariable int id) {
         this.agendamentoService.excluir(owner_id, id); // Usa o buscar para garantir que existe
        return ResponseEntity.noContent().build();
    }
}