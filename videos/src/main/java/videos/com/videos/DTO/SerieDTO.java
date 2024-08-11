package videos.com.videos.DTO;

import videos.com.videos.Models.Categoria;

import java.util.List;

public record SerieDTO(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String poster,
                       String sinopse,
                       List<EpisodioDTO> episodiosRecentes) { }
