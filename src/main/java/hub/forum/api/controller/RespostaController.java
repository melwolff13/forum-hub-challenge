package hub.forum.api.controller;

import hub.forum.api.domain.resposta.DadosAtualizacaoResposta;
import hub.forum.api.domain.resposta.DadosDetalhamentoResposta;
import hub.forum.api.domain.resposta.DadosResposta;
import hub.forum.api.service.RespostaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid DadosResposta dados) {
        return ResponseEntity.ok(respostaService.responderTopico(dados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<DadosDetalhamentoResposta>> listar(@PathVariable Long id, @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return ResponseEntity.ok(respostaService.respostasPorTopico(id, paginacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoResposta dados) {
        return ResponseEntity.ok(respostaService.atualizarResposta(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        respostaService.deletarResposta(id);
        return ResponseEntity.noContent().build();
    }
}
