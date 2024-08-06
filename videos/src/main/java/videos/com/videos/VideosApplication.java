package videos.com.videos;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import videos.com.videos.Principal.Principal;
import videos.com.videos.Repository.SerieRepositorio;


@SpringBootApplication
public class VideosApplication   {
	public static void main(String[] args) {
		SpringApplication.run(VideosApplication.class, args);
	}
}
