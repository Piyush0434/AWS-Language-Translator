//package com.TranslationBot.TranslationBot;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.web.bind.annotation.*;
//import software.amazon.awssdk.core.SdkBytes;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.lambda.LambdaClient;
//import software.amazon.awssdk.services.lambda.model.InvokeRequest;
//import software.amazon.awssdk.services.lambda.model.InvokeResponse;
//
//@RestController
//@RequestMapping("/api/translate")
//@CrossOrigin(origins = "*")
//public class TranslationController {
//
//    private final LambdaClient lambdaClient;
//    private final ObjectMapper objectMapper;
//
//    public TranslationController() {
//        this.lambdaClient = LambdaClient.builder()
//                .region(Region.US_EAST_1) // Your region
//                .build();
//        this.objectMapper = new ObjectMapper();
//    }
//
//    @PostMapping
//    public ActualTranslationResponse translate(@RequestBody TranslationRequest request) throws Exception {
//        // Prepare payload for Lambda
//        String jsonPayload = objectMapper.writeValueAsString(request);
//
//        // Invoke Lambda
//        InvokeRequest invokeRequest = InvokeRequest.builder()
//                .functionName("LexTranslateFunction") // Replace
//                .payload(SdkBytes.fromUtf8String(jsonPayload))
//                .build();
//
//        InvokeResponse invokeResponse = lambdaClient.invoke(invokeRequest);
//
//        JsonNode fullResponse = objectMapper.readTree(invokeResponse.payload().asUtf8String());
//
//        if (fullResponse.has("translatedText")) {
//            String translatedText = fullResponse.get("translatedText").asText();
//            return new ActualTranslationResponse(translatedText);
//        } else {
//            return new ActualTranslationResponse("Translation failed. No translatedText field.");
//        }
//
//    }
//
//}
package com.TranslationBot.TranslationBot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.InvokeRequest;
import software.amazon.awssdk.services.lambda.model.InvokeResponse;

@RestController
@RequestMapping("/api/translate")
@CrossOrigin(origins = "*")
public class TranslationController {

    private final LambdaClient lambdaClient;
    private final ObjectMapper objectMapper;

    public TranslationController() {
        this.lambdaClient = LambdaClient.builder()
                .region(Region.US_EAST_1) // Update if needed
                .build();
        this.objectMapper = new ObjectMapper();
    }

    @PostMapping
    public ResponseEntity<?> translate(@RequestBody TranslationRequest request) throws Exception {
        if (request.getText() == null || request.getText().isEmpty() ||
                request.getTargetLanguage() == null || request.getTargetLanguage().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Invalid input. Provide valid 'text' and supported 'targetLanguage' (e.g., French, Spanish).");
        }

        // Prepare payload for Lambda
        String jsonPayload = objectMapper.writeValueAsString(request);

        // Invoke Lambda
        InvokeRequest invokeRequest = InvokeRequest.builder()
                .functionName("LexTranslateFunction") // Replace with your Lambda function name
                .payload(SdkBytes.fromUtf8String(jsonPayload))
                .build();

        InvokeResponse invokeResponse = lambdaClient.invoke(invokeRequest);

        JsonNode fullResponse = objectMapper.readTree(invokeResponse.payload().asUtf8String());

        if (fullResponse.has("translatedText")) {
            String translatedText = fullResponse.get("translatedText").asText();
            String sourceLanguage = fullResponse.has("detectedSourceLanguage") ? fullResponse.get("detectedSourceLanguage").asText() : "unknown";
            String targetLanguage = fullResponse.has("detectedTargetLanguage") ? fullResponse.get("detectedTargetLanguage").asText() : request.getTargetLanguage();

            return ResponseEntity.ok(new ActualTranslationResponse(translatedText, sourceLanguage, targetLanguage));
        } else {
            return ResponseEntity.status(500)
                    .body(new ActualTranslationResponse("Translation failed. No 'translatedText' field in response."));
        }

    }
}
