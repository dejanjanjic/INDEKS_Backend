package net.etfbl.indeks.dto;



public class AddPrivateGroupChatDTO
{

    private String name;

    public AddPrivateGroupChatDTO() {
    }


    public AddPrivateGroupChatDTO(String name) {
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

