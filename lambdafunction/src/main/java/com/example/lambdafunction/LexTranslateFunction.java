package com.example.lambdafunction;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.AmazonTranslateClientBuilder;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import java.util.HashMap;
import java.util.Map;

public class LexTranslateFunction implements RequestHandler<Map<String, Object>, Map<String, Object>> {

//     @Override
//     public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
//      try {
//         String userText = (String) input.get("text");
//         String targetLang = mapLanguage((String) input.get("targetLanguage"));

//         if (userText == null || targetLang == null) {
//             return Map.of("translatedText", "Invalid input. Provide both text and targetLanguage.");
//         }

//         AmazonTranslate translate = AmazonTranslateClientBuilder.defaultClient();
//         TranslateTextRequest request = new TranslateTextRequest()
//                 .withText(userText)
//                 .withSourceLanguageCode("en")
//                 .withTargetLanguageCode(targetLang);

//         TranslateTextResult result = translate.translateText(request);
//         return Map.of("translatedText", result.getTranslatedText());

//     } catch (Exception e) {
//         return Map.of("translatedText", "Error: " + e.getMessage());
//     }
// }

@Override
public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
    try {
        String userText = (String) input.get("text");
        String targetLangInput = (String) input.get("targetLanguage");

        if (userText == null || userText.isEmpty() || targetLangInput == null || targetLangInput.isEmpty()) {
            return Map.of("translatedText", "Invalid input. Provide non-empty 'text' and 'targetLanguage'.");
        }

        String targetLangCode = mapLanguage(targetLangInput);

        if (targetLangCode == null) {
            return Map.of("translatedText", "Unsupported language: " + targetLangInput + ". Use supported language names like 'French', 'Spanish', etc.");
        }

        AmazonTranslate translate = AmazonTranslateClientBuilder.defaultClient();

        // Set source language as "auto" so AWS Translate detects it
        TranslateTextRequest request = new TranslateTextRequest()
                .withText(userText)
                .withSourceLanguageCode("auto")
                .withTargetLanguageCode(targetLangCode);

        TranslateTextResult result = translate.translateText(request);

        // Get detected source language code from result
        String detectedSourceLanguage = result.getSourceLanguageCode();

        Map<String, Object> response = new HashMap<>();
        response.put("translatedText", result.getTranslatedText());
        response.put("detectedTargetLanguage", mapCodeToLanguage(targetLangCode));
        response.put("detectedSourceLanguage", mapCodeToLanguage(detectedSourceLanguage));


        return response;

    } catch (Exception e) {
        return Map.of("translatedText", "Error: " + e.getMessage());
    }
}


    private String mapLanguage(String language) {
        if (language == null) return null;

        switch (language.toLowerCase().trim()) {
            case "amharic": return "am";
    case "arabic": return "ar";
    case "armenian": return "hy";
    case "azerbaijani": return "az";
    case "bengali": return "bn";
    case "bosnian": return "bs";
    case "bulgarian": return "bg";
    case "catalan": return "ca";
    case "chinese": return "zh";
    case "chinese (simplified)": return "zh";
    case "chinese (traditional)": return "zh-TW";
    case "croatian": return "hr";
    case "czech": return "cs";
    case "danish": return "da";
    case "dutch": return "nl";
    case "english": return "en";
    case "estonian": return "et";
    case "farsi": return "fa";
    case "finnish": return "fi";
    case "french": return "fr";
    case "french (canada)": return "fr-CA";
    case "georgian": return "ka";
    case "german": return "de";
    case "greek": return "el";
    case "gujarati": return "gu";
    case "hausa": return "ha";
    case "hebrew": return "he";
    case "hindi": return "hi";
    case "hungarian": return "hu";
    case "icelandic": return "is";
    case "indonesian": return "id";
    case "irish": return "ga";
    case "italian": return "it";
    case "japanese": return "ja";
    case "kannada": return "kn";
    case "kazakh": return "kk";
    case "khmer": return "km";
    case "korean": return "ko";
    case "lao": return "lo";
    case "latvian": return "lv";
    case "lithuanian": return "lt";
    case "macedonian": return "mk";
    case "malay": return "ms";
    case "malayalam": return "ml";
    case "maltese": return "mt";
    case "marathi": return "mr";
    case "mongolian": return "mn";
    case "nepali": return "ne";
    case "norwegian": return "no";
    case "pashto": return "ps";
    case "persian": return "fa";
    case "polish": return "pl";
    case "portuguese": return "pt";
    case "portuguese (portugal)": return "pt-PT";
    case "punjabi": return "pa";
    case "romanian": return "ro";
    case "russian": return "ru";
    case "serbian": return "sr";
    case "sinhala": return "si";
    case "slovak": return "sk";
    case "slovenian": return "sl";
    case "somali": return "so";
    case "spanish": return "es";
    case "spanish (mexico)": return "es-MX";
    case "swahili": return "sw";
    case "swedish": return "sv";
    case "tamil": return "ta";
    case "telugu": return "te";
    case "thai": return "th";
    case "turkish": return "tr";
    case "ukrainian": return "uk";
    case "urdu": return "ur";
    case "uzbek": return "uz";
    case "vietnamese": return "vi";
    case "welsh": return "cy";
    case "zulu": return "zu";
            default: return null;
        }
    }

    private String mapCodeToLanguage(String code) {
    if (code == null) return null;

    switch (code.toLowerCase()) {
        case "am": return "Amharic";
    case "ar": return "Arabic";
    case "hy": return "Armenian";
    case "az": return "Azerbaijani";
    case "bn": return "Bengali";
    case "bs": return "Bosnian";
    case "bg": return "Bulgarian";
    case "ca": return "Catalan";
    case "zh": return "Chinese (Simplified)";
    case "zh-tw": return "Chinese (Traditional)";
    case "hr": return "Croatian";
    case "cs": return "Czech";
    case "da": return "Danish";
    case "nl": return "Dutch";
    case "en": return "English";
    case "et": return "Estonian";
    case "fa": return "Farsi";
    case "fi": return "Finnish";
    case "fr": return "French";
    case "fr-ca": return "French (Canada)";
    case "ka": return "Georgian";
    case "de": return "German";
    case "el": return "Greek";
    case "gu": return "Gujarati";
    case "ha": return "Hausa";
    case "he": return "Hebrew";
    case "hi": return "Hindi";
    case "hu": return "Hungarian";
    case "is": return "Icelandic";
    case "id": return "Indonesian";
    case "ga": return "Irish";
    case "it": return "Italian";
    case "ja": return "Japanese";
    case "kn": return "Kannada";
    case "kk": return "Kazakh";
    case "ko": return "Korean";
    case "lv": return "Latvian";
    case "lt": return "Lithuanian";
    case "mk": return "Macedonian";
    case "ms": return "Malay";
    case "ml": return "Malayalam";
    case "mt": return "Maltese";
    case "mr": return "Marathi";
    case "mn": return "Mongolian";
    case "no": return "Norwegian";
    case "ps": return "Pashto";
    case "pl": return "Polish";
    case "pt": return "Portuguese";
    case "pt-pt": return "Portuguese (Portugal)";
    case "pa": return "Punjabi";
    case "ro": return "Romanian";
    case "ru": return "Russian";
    case "sr": return "Serbian";
    case "si": return "Sinhala";
    case "sk": return "Slovak";
    case "sl": return "Slovenian";
    case "so": return "Somali";
    case "es": return "Spanish";
    case "es-mx": return "Spanish (Mexico)";
    case "sw": return "Swahili";
    case "sv": return "Swedish";
    case "ta": return "Tamil";
    case "te": return "Telugu";
    case "th": return "Thai";
    case "tr": return "Turkish";
    case "uk": return "Ukrainian";
    case "ur": return "Urdu";
    case "uz": return "Uzbek";
    case "vi": return "Vietnamese";
    case "cy": return "Welsh";
    case "zu": return "Zulu";
        default: return code; // fallback to code if unknown
    }
}

    private String extractSlotValue(Map<String, Object> slots, String slotName) {
        if (slots == null || !slots.containsKey(slotName)) return null;

        Object slot = slots.get(slotName);
        if (slot instanceof Map) {
            Map<String, Object> valueMap = (Map<String, Object>) ((Map<String, Object>) slot).get("value");
            if (valueMap != null && valueMap.containsKey("interpretedValue")) {
                return (String) valueMap.get("interpretedValue");
            }
        }
        return null;
    }


//     // ✅ Maps user-selected language to AWS Translate codes
//     private String mapLanguage(String language) {
//     if (language == null) return null;

//     switch (language.toLowerCase()) {
//         case "afrikaans": return "af";
//         case "albanian": return "sq";
//         case "amharic": return "am";
//         case "arabic": return "ar";
//         case "armenian": return "hy";
//         case "azerbaijani": return "az";
//         case "bengali": return "bn";
//         case "bosnian": return "bs";
//         case "bulgarian": return "bg";
//         case "catalan": return "ca";
//         case "chinese": return "zh";
//         case "chinese (simplified)": return "zh";
//         case "chinese (traditional)": return "zh-TW";
//         case "croatian": return "hr";
//         case "czech": return "cs";
//         case "danish": return "da";
//         case "dari": return "fa-AF";
//         case "dutch": return "nl";
//         case "english": return "en";
//         case "estonian": return "et";
//         case "farsi": return "fa";
//         case "filipino": return "tl";
//         case "finnish": return "fi";
//         case "french": return "fr";
//         case "french (canada)": return "fr-CA";
//         case "georgian": return "ka";
//         case "german": return "de";
//         case "greek": return "el";
//         case "gujarati": return "gu";
//         case "haitian creole": return "ht";
//         case "hausa": return "ha";
//         case "hebrew": return "he";
//         case "hindi": return "hi";
//         case "hungarian": return "hu";
//         case "icelandic": return "is";
//         case "indonesian": return "id";
//         case "irish": return "ga";
//         case "italian": return "it";
//         case "japanese": return "ja";
//         case "kannada": return "kn";
//         case "kazakh": return "kk";
//         case "korean": return "ko";
//         case "latvian": return "lv";
//         case "lithuanian": return "lt";
//         case "macedonian": return "mk";
//         case "malay": return "ms";
//         case "malayalam": return "ml";
//         case "maltese": return "mt";
//         case "marathi": return "mr";
//         case "mongolian": return "mn";
//         case "norwegian": return "no";
//         case "pashto": return "ps";
//         case "polish": return "pl";
//         case "portuguese": return "pt";
//         case "portuguese (portugal)": return "pt-PT";
//         case "punjabi": return "pa";
//         case "romanian": return "ro";
//         case "russian": return "ru";
//         case "serbian": return "sr";
//         case "sinhala": return "si";
//         case "slovak": return "sk";
//         case "slovenian": return "sl";
//         case "somali": return "so";
//         case "spanish": return "es";
//         case "spanish (mexico)": return "es-MX";
//         case "swahili": return "sw";
//         case "swedish": return "sv";
//         case "tamil": return "ta";
//         case "telugu": return "te";
//         case "thai": return "th";
//         case "turkish": return "tr";
//         case "ukrainian": return "uk";
//         case "urdu": return "ur";
//         case "uzbek": return "uz";
//         case "vietnamese": return "vi";
//         case "welsh": return "cy";
//         default: return null; // Language not found
//     }
// }


    // ✅ Extracts sessionId safely & prevents LinkedHashMap cast errors
    private String getSessionId(Map<String, Object> sessionState) {
        if (sessionState == null) return "default-session";  // Fallback

        Object sessionIdObj = sessionState.get("sessionId");
        if (sessionIdObj instanceof String) {
            return (String) sessionIdObj;
        }

        return "default-session"; // Fallback if sessionId is missing
    }


    // ✅ Builds Lex response with sessionId and intent
    private Map<String, Object> buildLexResponse(String message, String sessionId, Map<String, Object> intent) {
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> sessionState = new HashMap<>();

        sessionState.put("dialogAction", Map.of("type", "Close"));
        sessionState.put("sessionId", sessionId);

        // ✅ Ensure intent has a name
        if (intent == null || !intent.containsKey("name")) {
            intent = new HashMap<>();
            intent.put("name", "TranslateIntent");  // ✅ Default name
            intent.put("slots", new HashMap<>());   // ✅ Ensure slots exist
        }

        sessionState.put("intent", intent);

        Map<String, Object> messageMap = Map.of(
                "contentType", "PlainText",
                "content", message
        );

        response.put("sessionState", sessionState);
        response.put("messages", new Object[]{messageMap});

        // Debugging: Print response to verify
        System.out.println("Response sent to Lex: " + response);

        return response;
    }

}

