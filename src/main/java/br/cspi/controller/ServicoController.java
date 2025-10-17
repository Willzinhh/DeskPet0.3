package br.cspi.controller;

import br.cspi.model.servico.Servico;
import br.cspi.service.ServicoService;
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
@RequestMapping("/servico")
@AllArgsConstructor
@Tag(name = "Serviço", description = "Endpoints para gerenciamento de Serviços (Banho, Tosa, etc.) oferecidos.")
public class ServicoController {

    private final ServicoService servicoService;

    @GetMapping("/listar")
    @Operation(summary = "Listar Serviços", description = "Lista todos os serviços cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Serviços retornada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Servico.class))),
    })
    public List<Servico> listar() {
        return servicoService.listar(); //
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Serviço por ID", description = "Retorna um serviço específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Servico.class))),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado.", content = @Content)
    })
    public Servico buscar(@Parameter(description = "ID do Serviço a ser buscado") @PathVariable long id) {
        return this.servicoService.buscar(id); //
    }

    @PostMapping
    @Operation(summary = "Criar novo Serviço", description = "Cria e salva um novo serviço.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Servico.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos.", content = @Content)
    })
    public Servico salvar(@RequestBody @Valid Servico servico) {
        return this.servicoService.salvar(servico); //
    }

    @PutMapping
    @Operation(summary = "Atualizar Serviço", description = "Atualiza os dados de um serviço existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço atualizado com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválios ou ID não encontrado.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado", content = @Content)

    })
    public Servico atualizar(@RequestBody @Valid Servico servico) {
        return this.servicoService.alterar(servico); //
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Serviço", description = "Remove um serviço pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço deletado com sucesso (No Content).", content = @Content),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado.", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Serviço a ser deletado") @PathVariable long id) {
        this.servicoService.excluir(id); //
        return ResponseEntity.noContent().build();
    }
}