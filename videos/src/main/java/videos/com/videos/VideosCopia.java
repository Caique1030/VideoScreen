package videos.com.videos;


//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import videos.com.videos.Principal.Principal;
//import videos.com.videos.Repository.SerieRepositorio;
//
//
//@SpringBootApplication
//public class VideosCopia implements CommandLineRunner {
//
//	@Autowired
//	private SerieRepositorio repositorio;  // Injeção de dependência
//
//	public static void main(String[] args) {
//		SpringApplication.run(VideosCopia.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		Principal principal = new Principal(repositorio);
//		principal.exibeMenu();  // Usando o bean gerenciado pelo Spring
//	}
//}
