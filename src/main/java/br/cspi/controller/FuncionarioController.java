package br.cspi.controller;

import br.cspi.model.cliente.DadosCliente;
import br.cspi.model.funcionario.DadosFuncionario;
import br.cspi.model.funcionario.Funcionario;
import br.cspi.model.funcionario.InputDadosFuncionario;
import br.cspi.model.servico.Servico;
import br.cspi.model.usuario.Owner;
import br.cspi.service.FuncionarioService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/funcionario")
@AllArgsConstructor
@Tag(name = "Funcionário", description = "Endpoints para gerenciamento de Funcionários (Colaboradores).")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;
    private final ServicoService servicoService;


    @GetMapping("/listar/{owner_id}")
    @Operation(summary = "Listar Funcionários", description = "Lista todos os funcionários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de Funcionários retornada com sucesso.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Funcionario.class))),
    })
    public List<DadosFuncionario> listar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id) {
        return funcionarioService.listar(owner_id); //
    }

    @GetMapping("/{owner_id}/{id}")
    @Operation(summary = "Buscar Funcionário por ID", description = "Retorna um funcionário específico pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Funcionário encontrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado.", content = @Content)
    })
    public DadosFuncionario buscar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id,@Parameter(description = "ID do Funcionário a ser buscado") @PathVariable long id) {
        return this.funcionarioService.buscarPorId(owner_id,id); //
    }


    @PostMapping("/{owner_id}")
    @Operation(summary = "Criar novo Funcionário", description = "Cria e salva um novo funcionário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Funcionario.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválios fornecidos.", content = @Content)
    })
    public ResponseEntity<DadosFuncionario> salvar(@Parameter(description = "ID do Proprietario") @PathVariable long owner_id, @RequestBody InputDadosFuncionario f, UriComponentsBuilder uriBuilder){
        Funcionario funcionarioEntity = new Funcionario();
        funcionarioEntity.setNome(f.nome());
        funcionarioEntity.setCpf(f.cpf());
        funcionarioEntity.setTelefone(f.telefone());
        funcionarioEntity.setCargo(f.cargo());
        funcionarioEntity.setSalario(f.salario());
        funcionarioEntity.setAtivo(f.ativo());
         DadosFuncionario funcionario = this.funcionarioService.salvar(owner_id, funcionarioEntity); //
        URI uri = uriBuilder.path("/funcionario/owner_id").buildAndExpand(funcionario.id()).toUri();
        return ResponseEntity.created(uri).body(funcionario);}



    @PutMapping("/{owner_id}")
    @Operation(summary = "Atualizar Funcionário", description = "Atualiza os dados de um funcionário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário atualizado com sucesso.",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Dados inválios ou ID não encontrado.", content = @Content),
            @ApiResponse(responseCode = "404", description = "Funcionario não encontrado", content = @Content)

    })
    public ResponseEntity<DadosFuncionario> atualizar(@Parameter (description = "ID do Funcionário a ser deletado") @PathVariable long owner_id,@RequestBody @Valid InputDadosFuncionario f, UriComponentsBuilder uriBuilder) {
        Funcionario funcionarioEntity = new Funcionario();
        funcionarioEntity.setId(f.id());
        funcionarioEntity.setNome(f.nome());
        funcionarioEntity.setCpf(f.cpf());
        funcionarioEntity.setTelefone(f.telefone());
        funcionarioEntity.setCargo(f.cargo());
        funcionarioEntity.setSalario(f.salario());
        funcionarioEntity.setAtivo(f.ativo());
        DadosFuncionario df = this.funcionarioService.editar(owner_id, funcionarioEntity);
        URI uri = uriBuilder.path("/funcionario/owner_id").buildAndExpand(df.id()).toUri();
        return ResponseEntity.created(uri).body(df);
    }

    @DeleteMapping("/{owner_id}/{id}")
    @Operation(summary = "Deletar Funcionário", description = "Remove um funcionário pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso (No Content).", content = @Content),
            @ApiResponse(responseCode = "404", description = "Funcionário não encontrado.", content = @Content)
    })
    public ResponseEntity deletar(@Parameter(description = "ID do Funcionário a ser deletado") @PathVariable long owner_id,@Parameter(description = "ID do Funcionário a ser deletado") @PathVariable long id) {
        this.funcionarioService.excluir(owner_id,id); //
        return ResponseEntity.noContent().build();
    }
//
//    @Operation(
//            summary = "Vincula um serviço a um funcionário",
//            description = "Associa um serviço existente a um funcionário existente. "
//                    + "Permite registrar quais serviços cada funcionário está habilitado a realizar."
//    )
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Serviço vinculado com sucesso",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = Funcionario.class))),
//            @ApiResponse(responseCode = "404", description = "Funcionário ou serviço não encontrado",
//                    content = @Content),
//            @ApiResponse(responseCode = "400", description = "Requisição inválida",
//                    content = @Content)
//    })
//    @PutMapping("/{idFuncionario}/servicos/{idServico}")
//    public ResponseEntity<?> vincularServico(
//            @Parameter(description = "ID do funcionário que receberá o serviço", example = "1")
//            @PathVariable Long idFuncionario,
//
//            @Parameter(description = "ID do serviço a ser vinculado", example = "2")
//            @PathVariable Long idServico) {
//
//        Funcionario funcionario = funcionarioService.buscarPorId(idFuncionario);
//        Servico servico = servicoService.buscar(idServico);
//
//        funcionario.getServicos().add(servico);
//        funcionarioService.salvar(funcionario);
//
//        return ResponseEntity.ok(funcionario);
//    }

}