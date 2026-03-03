package hub.forum.api.controller;

import hub.forum.api.domain.usuario.DadosNovoUsuario;
import hub.forum.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cadastro")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosNovoUsuario dados) {
        return ResponseEntity.ok(usuarioService.cadastrarUsuario(dados));
    }

}
