//package com.TranslationBot.TranslationBot;
//
//import java.util.List;
//
//public class LambdaInnerResponse {
//    private SessionState sessionState;
//    private List<Message> messages;
//
//    public LambdaInnerResponse() {}
//
//    public SessionState getSessionState() { return sessionState; }
//    public void setSessionState(SessionState sessionState) { this.sessionState = sessionState; }
//
//    public List<Message> getMessages() { return messages; }
//    public void setMessages(List<Message> messages) { this.messages = messages; }
//}
//
//class SessionState {
//    private DialogAction dialogAction;
//    private String sessionId;
//    private Intent intent;
//
//    public SessionState() {}
//
//    public DialogAction getDialogAction() { return dialogAction; }
//    public void setDialogAction(DialogAction dialogAction) { this.dialogAction = dialogAction; }
//    public String getSessionId() { return sessionId; }
//    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
//    public Intent getIntent() { return intent; }
//    public void setIntent(Intent intent) { this.intent = intent; }
//}
//
//class DialogAction {
//    private String type;
//
//    public DialogAction() {}
//
//    public String getType() { return type; }
//    public void setType(String type) { this.type = type; }
//}
//
//class Intent {
//    private String name;
//
//    public Intent(String name) {
//        this.name = name;
//    }
//
//    public String getName() { return name; }
//    public void setName(String name) { this.name = name; }
//}
//
//class Message {
//    private String content;
//    private String contentType;
//
//    public Message() {}
//
//    public String getContent() { return content; }
//    public void setContent(String content) { this.content = content; }
//
//    public String getContentType() { return contentType; }
//    public void setContentType(String contentType) { this.contentType = contentType; }
//}
