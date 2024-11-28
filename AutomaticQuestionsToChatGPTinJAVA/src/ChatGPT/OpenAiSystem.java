package ChatGPT;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class OpenAiSystem {

    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String API_KEY = "KeyOpenAI";

    public static void main(String[] args) {
        // Lista de perguntas
        String[] perguntas = {
            "Qual é o significado de inteligência artificial?",
            "Como funciona o aprendizado de máquina?",
            "Quais são os benefícios da automação?"
        };

        // Loop para enviar perguntas
        for (String pergunta : perguntas) {
            try {
                String resposta = enviarPergunta(pergunta);
                System.out.println("Pergunta: " + pergunta);
                System.out.println("Resposta: " + resposta);
                Thread.sleep(5000); // Pausa de 5 segundos entre perguntas
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String enviarPergunta(String pergunta) throws Exception {
        // Configuração do cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Criação do corpo da requisição
        JsonObject mensagem = new JsonObject();
        mensagem.addProperty("role", "user");
        mensagem.addProperty("content", pergunta);

        JsonArray mensagensArray = new JsonArray();
        mensagensArray.add(mensagem);

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", "gpt-3.5-turbo");
        requestBody.add("messages", mensagensArray);

        // Construção da requisição HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        // Enviar a requisição e receber a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Imprimir a resposta completa para depuração
        System.out.println("Resposta da API: " + response.body());

        // Processar o JSON da resposta
        JsonObject jsonResponse = com.google.gson.JsonParser.parseString(response.body()).getAsJsonObject();

        if (jsonResponse.has("choices") && jsonResponse.getAsJsonArray("choices") != null) {
            return jsonResponse.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .get("message").getAsJsonObject()
                    .get("content").getAsString();
        } else {
            throw new IllegalStateException("Campo 'choices' está ausente ou vazio na resposta da API: " + response.body());
        }
    }
}
