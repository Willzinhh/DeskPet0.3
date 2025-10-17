package br.cspi.controller;

import br.cspi.model.agendamento.Agendamento;
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

import java.util.List;

@RestController
@RequestMapping("/agendamento")
@AllArgsConstructor
@Tag(name = "Agendamento", description = "Endpoints para gerenciamento de Agendamentos (Consultas, Serviços) dos Pets.")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;


    @GetMapping("/listar")
    @Operation(summary = "Listar Agendamentos", description = "Lista todos os agendamentos cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Agendamentos retornada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Agendamento.class))),
    })
    public List<Agendamento> listar() {
        return agendamentoService.listar(); //
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Agendamento por ID", description = "Retorna um agendamento específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Agendamento encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.", content = @Content)
    })
    public Agendamento buscar(@Parameter(description = "ID do Agendamento a ser buscado") @PathVariable int id) {
        return this.agendamentoService.getById(id); //
    }

    @PostMapping
    @Operation(summary = "Criar novo Agendamento", description = "Cria e salva um novo agendamento.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos.", content = @Content)
    })
    public Agendamento salvar(@RequestBody @Valid Agendamento agendamento) {
        return this.agendamentoService.salvar(agendamento); //
    }

    @PutMapping
    @Operation(summary = "Atualizar Agendamento", description = "Atualiza os dados de um agendamento existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento atualizado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Agendamento.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios", content = @Content),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado", content = @Content)

    })
    public Agendamento atualizar(@RequestBody @Valid Agendamento agendamento) {
        return this.agendamentoService.atualizar(agendamento); //
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Agendamento", description = "Remove um agendamento pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Agendamento deletado com sucesso (No Content).", content = @Content),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado.", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Agendamento a ser deletado") @PathVariable int id) {
        Agendamento agendamento = agendamentoService.getById(id); // Usa o buscar para garantir que existe
        this.agendamentoService.excluir(agendamento); //
        return ResponseEntity.noContent().build();
    }
}