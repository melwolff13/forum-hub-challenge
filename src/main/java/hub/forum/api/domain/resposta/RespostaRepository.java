package hub.forum.api.domain.resposta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {
    Page<Resposta> findAllByTopicoIdAndAtivoTrue(Long id, Pageable paginacao);

    Optional<Resposta> findByIdAndAtivoTrue(Long id);
}
