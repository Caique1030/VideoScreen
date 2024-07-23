package videos.com.videos.Service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import com.theokanning.openai.OpenAiHttpException;

import java.util.concurrent.TimeUnit;

public class ConsultaChatGPT {
    private static final long RATE_LIMIT_INTERVAL_MS = 1000; // 1 segundo
    private static long lastRequestTime = 0;

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

        OpenAiService service = new OpenAiService("Minhachave");

        CompletionRequest requisicao = CompletionRequest.builder()
                .model("texto-incorporado-3-grande") // Atualizado para um modelo válido
                .prompt("traduza para o português o texto: " + texto)
                .maxTokens(500) // Ajustado conforme necessidade
                .temperature(0.7)
                .build();

        try {
            var resposta = service.createCompletion(requisicao);
            lastRequestTime = System.currentTimeMillis();
            return resposta.getChoices().get(0).getText();
        } catch (OpenAiHttpException e) {
            return "Erro na chamada à API OpenAI: " + e.getMessage();
        } catch (Exception e) {
            return "Erro inesperado: " + e.getMessage();
        }
    }
}
