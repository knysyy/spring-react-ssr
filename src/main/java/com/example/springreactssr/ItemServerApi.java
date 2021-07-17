package com.example.springreactssr;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ItemServerApi implements ServerApi {
    private final ItemApiService itemApiService;

    public ItemServerApi(ItemApiService itemApiService) {
        this.itemApiService = itemApiService;
    }

    public String getList() throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(this.itemApiService.getList());
    }
}
