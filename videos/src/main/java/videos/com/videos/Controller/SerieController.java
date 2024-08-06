package videos.com.videos.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import videos.com.videos.DTO.SerirDTO;
import videos.com.videos.Models.Serie;
import videos.com.videos.Repository.SerieRepositorio;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SerieController {

    @Autowired
    private SerieRepositorio repositorio;

    @GetMapping("/series")
    public List<SerirDTO> ObterSeries(){
        return (List<SerirDTO>) repositorio.findAll()
                .stream()
                .map(s-> new SerirDTO(s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getAvaliacao(),
                        s.getGenero(),
                        s.getAtores(),
                        s.getPoster(),
                        s.getSinopse()))
                .collect(Collectors.toList());
    }



}
