package service;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Properties;

public class AIService {

    private static String apiKey;

    static {
        try {
            InputStream input =
                    AIService.class.getClassLoader()
                            .getResourceAsStream("config.properties");

            Properties properties = new Properties();

            properties.load(input);

            apiKey = properties.getProperty(
                    "GROQ_API_KEY"
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String analyzeCode(String code) {

        try {

            OkHttpClient client =
                    new OkHttpClient();

            JSONObject requestBody =
                    new JSONObject();

            requestBody.put(
                    "model",
                    "llama-3.3-70b-versatile"
            );

            requestBody.put(
                    "messages",
                    new JSONArray()
                            .put(
                                    new JSONObject()
                                            .put("role", "user")
                                            .put(
                                                    "content",
                                                    "Review this Java code and provide improvements:\n\n"
                                                            + code
                                            )
                            )
            );

            Request request =
                    new Request.Builder()
                            .url(
                                    "https://api.groq.com/openai/v1/chat/completions"
                            )
                            .addHeader(
                                    "Authorization",
                                    "Bearer " + apiKey
                            )
                            .addHeader(
                                    "Content-Type",
                                    "application/json"
                            )
                            .post(
                                    RequestBody.create(
                                            requestBody.toString(),
                                            MediaType.parse(
                                                    "application/json"
                                            )
                                    )
                            )
                            .build();

            Response response =
                    client.newCall(request)
                            .execute();

            String body =
                    response.body().string();

            System.out.println(body);

            JSONObject json = new JSONObject(body);
                        

String aiResponse =
        json.getJSONArray("choices")
            .getJSONObject(0)
            .getJSONObject("message")
            .getString("content");

aiResponse = aiResponse.replace("```java", "");
aiResponse = aiResponse.replace("```", "");
aiResponse = aiResponse.replace("###", "");
aiResponse = aiResponse.replace("####", "");
aiResponse = aiResponse.replace("**", "");
aiResponse = aiResponse.replace("*", "");

return aiResponse;
                        
        }
        catch (Exception e) {
    e.printStackTrace();
    return e.getMessage();
}
}    
}