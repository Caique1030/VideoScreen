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
                .map(s -> {
                    // Aqui você pode buscar os episódios recentes para a série s
                    List<EpisodioDTO> episodiosRecentes = repositorio.obterEpisodiosMaisRecentesPorSerie(s.getId())
                            .stream()
                            .map(e -> new EpisodioDTO(e.getTemporada(), e.getNumeroEpisodio(), e.getTitulo()))
                            .collect(Collectors.toList());

                    return new SerieDTO(
                            s.getId(),
                            s.getTitulo(),
                            s.getTotalTemporadas(),
                            s.getAvaliacao(),
                            s.getGenero(),
                            s.getAtores(),
                            s.getPoster(),
                            s.getSinopse(),
                            episodiosRecentes // Inclua a lista de episódios recentes
                    );
                })
                .collect(Collectors.toList());
    }




    public List<SerieDTO> obterLancamentosMaisRecentes() {
        // Obtém as séries mais recentes
        List<Serie> series = repositorio.lancamentosMaisRecentes();

        // Verifique se a lista de séries está vazia
        if (series.isEmpty()) {
            return Collections.emptyList();
        }

        // Limitar os resultados para os 5 mais recentes
        List<Serie> top5Series = series.stream()
                .limit(5)
                .collect(Collectors.toList());

        return top5Series.stream()
                .map(serie -> {
                    // Obtém os episódios mais recentes para cada série
                    List<EpisodioDTO> episodiosRecentes = repositorio.obterEpisodiosMaisRecentesPorSerie(serie.getId())
                            .stream()
                            .map(e -> new EpisodioDTO(
                                    e.getTemporada(),      // Ajuste conforme o que você tem no Episodio
                                    e.getNumeroEpisodio(), // Ajuste conforme o que você tem no Episodio
                                    e.getTitulo()          // Ajuste conforme o que você tem no Episodio
                            ))
                            .collect(Collectors.toList());

                    // Criação do SerieDTO com todos os parâmetros necessários, incluindo a lista de EpisodioDTO
                    return new SerieDTO(
                            serie.getId(),
                            serie.getTitulo(),
                            serie.getTotalTemporadas(),
                            serie.getAvaliacao(),
                            serie.getGenero(),
                            serie.getAtores(),
                            serie.getPoster(),
                            serie.getSinopse(),
                            episodiosRecentes
                    );
                })
                .collect(Collectors.toList());
    }


    public SerieDTO obterPorId(Long id) {
        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()) {
            Serie s = serie.get();
            List<EpisodioDTO> episodiosRecentes = repositorio.obterEpisodiosMaisRecentesPorSerie(s.getId())
                    .stream()
                    .map(e -> new EpisodioDTO(
                            e.getTemporada(),
                            e.getNumeroEpisodio(),
                            e.getTitulo()
                    ))
                    .collect(Collectors.toList());

            return new SerieDTO(
                    s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getAvaliacao(),
                    s.getGenero(),
                    s.getAtores(),
                    s.getPoster(),
                    s.getSinopse(),
                    episodiosRecentes
            );
        }
        return null;
    }




    public List<EpisodioDTO> obterTodasTemporadas(Long id) {
        Optional<Serie> serie = repositorio.findById(id);

        if (serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream().map(e-> new EpisodioDTO(e.getTemporada(),e.getNumeroEpisodio(),e.getTitulo()))
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

        // Converte a lista de entidades Serie para uma lista de SerieDTO
        return series.stream()
                .map(s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getAvaliacao(),
                        s.getGenero(),
                        s.getAtores(),
                        s.getPoster(),
                        s.getSinopse(),
                        // Inclua a lista de episódios se disponível ou use uma lista vazia
                        Collections.emptyList() // Ajuste conforme necessário se você tiver episódios
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
