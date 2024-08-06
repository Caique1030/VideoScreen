package videos.com.videos.DTO;

import videos.com.videos.Models.Categoria;

public record SerirDTO(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       Double avaliacao,
                       Categoria genero,
                       String atores,
                       String poster,
                       String sinopse) {
}
