package com.example.springreactssr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemApiController {
    private final ItemApiService itemApiService;

    public ItemApiController(ItemApiService itemApiService) {
        this.itemApiService = itemApiService;
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Item> getList() {
        return itemApiService.getList();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addItem(@RequestBody NewItem item) {
        itemApiService.addItem(item.content);
    }
}
