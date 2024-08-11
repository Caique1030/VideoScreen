package videos.com.videos.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import videos.com.videos.Models.Categoria;
import videos.com.videos.Models.Episodio;
import videos.com.videos.Models.Serie;

import java.util.List;
import java.util.Optional;

public interface SerieRepositorio extends JpaRepository<Serie,Long> {

    Optional<Serie> findByTituloContainingIgnoreCase(String nomeSerie);

    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacaoGreaterThanEqual(String nomeAtor, double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacaoDesc();

    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacaoGreaterThanEqual(int totalTemporadas, double avaliacao);
    @Query("select s from Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.avaliacao >= :avaliacao")
    List<Serie> seriesPorTemporadaEAValiacao(int totalTemporadas, double avaliacao);
    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE e.titulo ILIKE %:trechoEpisodio%")
    List<Episodio> episodiosPorTrecho(String trechoEpisodio);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("SELECT e FROM Serie s JOIN s.episodios e WHERE s = :serie AND YEAR(e.dataLancamento) >= :anoLancamento")
    List<Episodio> episodiosPorSerieEAno(Serie serie, int anoLancamento);

    @Query("SELECT s FROM Serie s JOIN s.episodios e " +
            "WHERE e.dataLancamento IS NOT NULL " +
            "ORDER BY e.dataLancamento DESC")
    List<Serie> lancamentosMaisRecentes();

    @Query("SELECT e FROM Episodio e " +
            "WHERE e.serie.id = :serieId " +
            "ORDER BY e.dataLancamento DESC")
    List<Episodio> obterEpisodiosMaisRecentesPorSerie(@Param("serieId") Long serieId);


    @Query("SELECT e FROM Serie s " +
            "JOIN s.episodios e " +
            "WHERE s.id = :id AND e.temporada = :numero")
    List<Episodio> obterEpisodiosPorTemporada(Long id, Long numero);
}