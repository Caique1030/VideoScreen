package videos.com.videos;

public class ConfiApi {
    private String endereco;
    private String apiKey;

    public ConfiApi(String endereco, String apiKey) {
        this.endereco = endereco;
        this.apiKey = apiKey;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getApiKey() {
        return apiKey;
    }
}
