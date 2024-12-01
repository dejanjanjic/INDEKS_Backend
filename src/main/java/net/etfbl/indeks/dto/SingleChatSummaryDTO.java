package net.etfbl.indeks.dto;

public class SingleChatSummaryDTO {

    private String id;
    private String name;

    private String sender;
    private String lastMessage;

    // Constructors, getters, and setters

    public SingleChatSummaryDTO(String id, String name,String sender, String lastMessage) {
        this.id = id;
        this.name = name;
        this.sender = sender;
        this.lastMessage = lastMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
