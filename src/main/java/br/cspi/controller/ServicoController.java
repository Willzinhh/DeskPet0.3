package br.cspi.controller;

import br.cspi.model.funcionario.DadosFuncionario;
import br.cspi.model.servico.DadosServico;
import br.cspi.model.servico.InputDadosServico;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/servico")
@AllArgsConstructor
@Tag(name = "Serviço", description = "Endpoints para gerenciamento de Serviços (Banho, Tosa, etc.) oferecidos.")
public class ServicoController {

    private final ServicoService servicoService;

    @GetMapping("/listar/{owner_id}")
    @Operation(summary = "Listar Serviços", description = "Lista todos os serviços cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Serviços retornada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Servico.class))),
    })
    public List<DadosServico> listar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id) {
        return servicoService.listar(owner_id); //
    }

    @GetMapping("/{owner_id}/{id}")
    @Operation(summary = "Buscar Serviço por ID", description = "Retorna um serviço específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serviço encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Servico.class))),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado.", content = @Content)
    })
    public DadosServico buscar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id,@Parameter(description = "ID do Serviço a ser buscado") @PathVariable long id) {
        return this.servicoService.buscar(owner_id, id); //
    }

    @GetMapping("/listarbyFuncionario/{owner_id}/{funcionario_id}")
    @Operation(summary = "Listar Serviços", description = "Lista todos os serviços cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Serviços retornada com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Servico.class))),
    })
    public List<DadosServico> listarByFuncionario(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id, @Parameter(description = "ID do Proprietario") @PathVariable long funcionario_id) {
        return servicoService.listarByFuncionario(owner_id, funcionario_id); //
    }

    @PostMapping("/{owner_id}")
    @Operation(summary = "Criar novo Serviço", description = "Cria e salva um novo serviço.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Serviço criado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Servico.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos.", content = @Content)
    })
    public ResponseEntity<DadosServico> salvar(@Parameter(description = "ID do Serviço a ser buscado") @PathVariable long owner_id, @RequestBody @Valid InputDadosServico servico, UriComponentsBuilder uriBuilder) {
        Servico servicoEntity = new Servico();
        servicoEntity.setId(servico.id());
        servicoEntity.setNome(servico.nome());
        servicoEntity.setDescricao(servico.descricao());
        servicoEntity.setValor(servico.valor());
        servicoEntity.setTempo(servico.tempo());

        DadosServico ds = this.servicoService.salvar(owner_id,servicoEntity);
        URI uri = uriBuilder.path("servico/{servico_id}").build(ds.id());
        return ResponseEntity.created(uri).body(ds);
    }

    @PutMapping("/{owner_id}")
    @Operation(summary = "Atualizar Serviço", description = "Atualiza os dados de um serviço existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço atualizado com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválios ou ID não encontrado.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado", content = @Content)

    })
    public DadosServico atualizar(@Parameter(description = "ID do Serviço a ser buscado") @PathVariable long owner_id, @RequestBody @Valid InputDadosServico servico) {
        Servico servicoEntity = new Servico();
        servicoEntity.setId(servico.id());
        servicoEntity.setNome(servico.nome());
        servicoEntity.setDescricao(servico.descricao());
        servicoEntity.setValor(servico.valor());
        servicoEntity.setTempo(servico.tempo());
        return this.servicoService.editar(owner_id, servicoEntity);//
    }

    @DeleteMapping("/{owner_id}/{id}")
    @Operation(summary = "Deletar Serviço", description = "Remove um serviço pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Serviço deletado com sucesso (No Content).", content = @Content),
            @ApiResponse(responseCode = "404", description = "Serviço não encontrado.", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Serviço a ser buscado") @PathVariable long owner_id, @Parameter(description = "ID do Serviço a ser deletado") @PathVariable long id) {
        this.servicoService.excluir(owner_id, id); //
        return ResponseEntity.noContent().build();
    }
}