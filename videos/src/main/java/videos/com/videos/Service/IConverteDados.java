package videos.com.videos.Service;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
