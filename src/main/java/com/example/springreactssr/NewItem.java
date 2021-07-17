package com.example.springreactssr;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewItem {
    public String content;

    @JsonCreator
    public NewItem(@JsonProperty("content") String content) {
        this.content = content;
    }
}
