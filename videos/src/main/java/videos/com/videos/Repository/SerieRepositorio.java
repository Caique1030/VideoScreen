package videos.com.videos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import videos.com.videos.Models.Serie;

import java.util.List;
import java.util.Optional;

public interface SerieRepositorio extends JpaRepository<Serie,Long> {

    Optional<Serie> findByTituloContainingIgnoreCase (String nomeSerie);
    List<Serie> findByAtorContainingIgnoreCase (String nomeAtor);
}
