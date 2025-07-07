package com.TranslationBot.TranslationBot;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TranslationRequest {
    private String text;
    private String targetLanguage;

    public TranslationRequest() {}

}
