package videos.com.videos.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import videos.com.videos.DTO.EpisodioDTO;
import videos.com.videos.DTO.SerieDTO;
import videos.com.videos.DTO.Service.SerieService;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService service;

    @GetMapping
    public List<SerieDTO> ObterSeries(){
        return service.ObterSeries();
    }
    @GetMapping("/top5")
    public List<SerieDTO> obtertop5series(){
        return service.obtertop5series();
    }
    @GetMapping("/lancamentos")
    public List<SerieDTO> obterLancamentos(){
        return service.obterLancamentosMaisRecentes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<SerieDTO> obterPorId(@PathVariable("id") Long id) {
        SerieDTO serie = service.obterPorId(id); // Correção: use a instância do serviço
        if (serie != null) {
            return ResponseEntity.ok(serie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obterTodasTemporadas(@PathVariable Long id){
        return service.obterTodasTemporadas(id);
    }
    @GetMapping("/{id}/temporadas/{numero}")
    public List<EpisodioDTO> obterTemporadasPorNumero(@PathVariable Long id,@PathVariable Long numero){
        return service.obterTemporadasPorNumero(id,numero);
    }
    @GetMapping("/categoria/{nomeGenero}")
    public List<SerieDTO> obterCategoria(@PathVariable String nomeGenero){
        return service.obterCategoria(nomeGenero);
    }
    @GetMapping("/search")
    public List<SerieDTO> buscarSeries(@RequestParam String q) {
        return service.buscarSeriesPorNome(q);
    }

}
