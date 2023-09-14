package poc;

import java.io.InputStream;
import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.bedrock.BedrockClient;
import software.amazon.awssdk.services.bedrock.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrock.model.InvokeModelResponse;

public class PlayingWithBedrock {

    private static String promptTemplate;
    
    static {

        InputStream is = PlayingWithBedrock.class.getResourceAsStream("/prompt.json");
        byte[] bytes = null;

        try {
            bytes = new byte[is.available()];
            is.read(bytes);
            promptTemplate = new String(bytes, Charset.defaultCharset());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String... args) throws Exception {

        final ObjectMapper objectMapper = new ObjectMapper();
        String myPrompt = promptTemplate.replace("##PROMPT##",
                "Where in Seattle I can buy home made bread?");

        try (BedrockClient bedrockClient = BedrockClient.builder().build()) {

            InvokeModelRequest invokeModelRequest = InvokeModelRequest.builder()
                .body(SdkBytes.fromString(myPrompt, Charset.defaultCharset()))
                .modelId("amazon.titan-tg1-large")
                .build();

            InvokeModelResponse imResponse = bedrockClient.invokeModel(invokeModelRequest);

            BedrockResult bedrockResult = objectMapper.readValue(
                imResponse.body().asUtf8String(), BedrockResult.class);

            System.out.println(bedrockResult.getResults()[0].getOutputText());

        }

    }

}
