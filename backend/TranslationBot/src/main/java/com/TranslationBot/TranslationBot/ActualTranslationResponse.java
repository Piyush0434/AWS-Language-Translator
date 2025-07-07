package com.TranslationBot.TranslationBot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualTranslationResponse {
    private String translatedText;
    private String detectedTargetLanguage;
    private String detectedSourceLanguage;

    public ActualTranslationResponse(String translatedText) {
        this.translatedText = translatedText;
        this.detectedTargetLanguage = null;
        this.detectedSourceLanguage = null;
    }
}
