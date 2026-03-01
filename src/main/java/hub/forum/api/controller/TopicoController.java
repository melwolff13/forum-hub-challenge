package hub.forum.api.controller;

import hub.forum.api.domain.topico.DadosAtualizacaoTopico;
import hub.forum.api.domain.topico.DadosDetalhamentoTopico;
import hub.forum.api.domain.topico.DadosNovoTopico;
import hub.forum.api.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid DadosNovoTopico dados) {
        return ResponseEntity.ok(topicoService.criarTopico(dados));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoTopico>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        return ResponseEntity.ok(topicoService.listarTodosTopicos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.detalharTopico(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoTopico dados) {
        return ResponseEntity.ok(topicoService.atualizarTopico(id, dados));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        topicoService.deletarTopico(id);
        return ResponseEntity.ok().build();
    }

}

