package com.example.springreactssr;

import java.util.UUID;

public class Item {
    String content;
    UUID id;

    public Item(String content, UUID id) {
        this.content = content;
        this.id = id;
    }
}
