package com.example.springreactssr;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItemApiService {
    private List<Item> items = new ArrayList<>();

    public List<Item> getList() {
        return items;
    }

    public void addItem(String content) {
        items.add(new Item(content, UUID.randomUUID()));
    }
}
