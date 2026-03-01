package hub.forum.api.domain.resposta;

import hub.forum.api.domain.topico.Topico;
import hub.forum.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "respostas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Resposta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    private LocalDateTime dataCriacao;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private Boolean ativo;

    public Resposta(DadosResposta dados, Topico topico, Usuario autor) {
        this.mensagem = dados.mensagem();
        this.topico = topico;
        this.dataCriacao = dados.dataCriacao();
        this.autor = autor;
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoResposta dados) {
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
    }

    public void deletar() {
        this.ativo = false;
    }
}
