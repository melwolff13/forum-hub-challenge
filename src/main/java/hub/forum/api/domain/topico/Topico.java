package hub.forum.api.domain.topico;

import hub.forum.api.domain.resposta.Resposta;
import hub.forum.api.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private String curso;
    @OneToMany
    private List<Resposta> respostas;

}
