package hub.forum.api.controller;

import hub.forum.api.domain.topico.DadosNovoTopico;
import hub.forum.api.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid DadosNovoTopico dados) {
        return topicoService.criarTopico(dados);
    }

}

