package videos.com.videos.DTO.Service;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConsultaChatGPT {

    private static final long RATE_LIMIT_INTERVAL_MS = 1000; // 1 segundo
    private static long lastRequestTime = 0;

    private static final String API_KEY = System.getenv("OPENAI_API_KEY"); // Certifique-se de definir esta variável
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static String obterTraducao(String texto) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastRequestTime < RATE_LIMIT_INTERVAL_MS) {
            try {
                TimeUnit.MILLISECONDS.sleep(RATE_LIMIT_INTERVAL_MS - (currentTime - lastRequestTime));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return "Erro: A thread foi interrompida.";
            }
        }

        OkHttpClient client = new OkHttpClient();

        JSONObject json = new JSONObject();
        json.put("model", "gpt-4");
        json.put("messages", new JSONArray().put(new JSONObject()
                .put("role", "user")
                .put("content", "Translate the following text to Portuguese: " + texto)));
        json.put("max_tokens", 500);
        json.put("temperature", 0.7);

        RequestBody body = RequestBody.create(json.toString(), MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject responseJson = new JSONObject(responseBody);
                String translatedText = responseJson.getJSONArray("choices")
                        .getJSONObject(0)
                        .getJSONObject("message")
                        .getString("content");
                lastRequestTime = System.currentTimeMillis();
                return translatedText.trim();
            } else {
                return "Erro na chamada à API OpenAI: " + response.message();
            }
        } catch (IOException e) {
            return "Erro na chamada à API OpenAI: " + e.getMessage();
        }
    }
}
