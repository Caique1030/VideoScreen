package videos.com.videos.DTO.Service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
