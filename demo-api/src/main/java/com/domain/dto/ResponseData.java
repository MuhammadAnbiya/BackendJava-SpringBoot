package com.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData <T>{ // Kelas Generic untuk menghandle berbagai type data

    private boolean status; // status yang akan cek nanti pada proses pemetaan data
    private List<String> messages = new ArrayList<>(); // Array yang akan menampung error dan ditampilkan pada terminal nantinya
    private T payload; // Type Generic artinya data bisa diisi dengan type data apa saja nantinya
    
    // Setter Getter
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public List<String> getMessages() {
        return messages;
    }
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
    public T getPayload() {
        return payload;
    }
    public void setPayload(T payload) {
        this.payload = payload;
    }

    
}
