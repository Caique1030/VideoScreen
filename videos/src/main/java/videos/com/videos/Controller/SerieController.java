package videos.com.videos.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import videos.com.videos.DTO.EpisodioDTO;
import videos.com.videos.DTO.SerieDTO;
import videos.com.videos.DTO.Service.SerieService;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

   @Autowired
   private SerieService servico;

    @GetMapping
    public List<SerieDTO> obterSeries() {
       return servico.ObterSeries();
    }

    @GetMapping("/top5")
    public List<SerieDTO> obterTop5Series() {
        return servico.obtertop5series();
    }

    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos() {
        return servico.obterLancamentos();
    }

    @GetMapping("/{id}")
    public SerieDTO obterPorId(@PathVariable Long id) {
        return servico.obterPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id){
        return servico.obterTodasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id, @PathVariable Long numero){
        return servico.obterTemporadasPorNumero(id, numero);
    }

    @GetMapping("/{id}/temporadas/top")
    public List<EpisodioDTO> obterTopEpisodios(@PathVariable Long id){
        return servico.obterTopEpisodios(id);
    }

    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obterSeriesPorCategoria(@PathVariable String nomeGenero){
        return servico.obterCategoria(nomeGenero);
    }
    @GetMapping("/search")
    public List<SerieDTO> buscarSeries(@RequestParam String q) {
        return servico.buscarSeriesPorNome(q);
    }

}
