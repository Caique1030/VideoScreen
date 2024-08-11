package videos.com.videos.DTO.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import videos.com.videos.DTO.EpisodioDTO;
import videos.com.videos.DTO.SerieDTO;
import videos.com.videos.Models.Categoria;
import videos.com.videos.Models.DadosSerie;
import videos.com.videos.Models.Serie;
import videos.com.videos.Repository.SerieRepositorio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class SerieService {
    @Autowired
    private SerieRepositorio repositorio;

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String API_URL = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";

    public List<SerieDTO> ObterSeries(){
        return converteSerie(repositorio.findAll());

    }
    public List<SerieDTO> obtertop5series() {
        return converteSerie(repositorio.findTop5ByOrderByAvaliacaoDesc());
    }
    private List<SerieDTO> converteSerie(List<Serie> series) {
        return series.stream()
                .map(s -> new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse()))
                .collect(Collectors.toList());
      }




      public List<SerieDTO> obterLancamentos() {
        return converteSerie(repositorio.lancamentosMaisRecentes());
    }

    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return new SerieDTO(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getAvaliacao(), s.getGenero(), s.getAtores(), s.getPoster(), s.getSinopse());
        }
        return null;
    }


    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obterTemporadasPorNumero(Long id, Long numero) {
        return repositorio.obterEpisodiosPorTemporada(id, numero)
                .stream()
                .map(e-> new EpisodioDTO(e.getTemporada(),e.getNumeroEpisodio(),e.getTitulo()))
                .collect(Collectors.toList());

    }
    public List<SerieDTO> obterCategoria(String nomeGenero) {
        Categoria categoria = Categoria.fromPortugues(nomeGenero);
        return converteSerie(repositorio.findByGenero(categoria));
    }
    public List<EpisodioDTO> obterTopEpisodios(Long id) {
        var serie = repositorio.findById(id).get();
        return repositorio.topEpisodiosPorSerie(serie)
                .stream()
                .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                .collect(Collectors.toList());
    }
    public List<SerieDTO> buscarSeriesPorNome(String nome) {
        // Busca as séries por nome
        Optional<Serie> series = repositorio.findByTituloContainingIgnoreCase(nome);
    
        // Se não encontrar nenhuma série, busque na web e então busque novamente
        if (series.isEmpty()) {
            buscarSerieWeb(nome); // Busca e salva a série na web
            series = repositorio.findByTituloContainingIgnoreCase(nome);
        }
        
        return series.stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getAvaliacao(),
                        s.getGenero(), // Certifique-se de que este é o tipo correto
                        s.getAtores(),
                        s.getPoster(),
                        s.getSinopse()
                ))
                .collect(Collectors.toList());
    }
    

    public void buscarSerieWeb(String nomeSerie) {
        var json = consumo.obterDados(API_URL + nomeSerie.replace(" ", "+") + API_KEY);
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        Serie serie = new Serie(dados);
        repositorio.save(serie);
        System.out.println("Série encontrada e salva: " + dados);
    }
}
