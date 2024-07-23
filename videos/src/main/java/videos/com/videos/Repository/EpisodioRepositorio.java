package videos.com.videos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import videos.com.videos.Models.Episodio;


public interface EpisodioRepositorio extends JpaRepository<Episodio,Long> {
}
