package com.example.springreactssr;

import java.util.UUID;

public class Item {
    public String content;
    public UUID id;

    public Item(String content, UUID id) {
        this.content = content;
        this.id = id;
    }
}
