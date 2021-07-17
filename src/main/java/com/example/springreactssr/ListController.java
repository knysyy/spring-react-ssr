package com.example.springreactssr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ListController extends AbstractController {

    private final ItemServerApi itemServerApi;

    public ListController(ItemServerApi itemServerApi) {
        this.itemServerApi = itemServerApi;
    }

    @GetMapping("/list")
    public String index(HttpServletRequest request, Model model) throws ScriptException, IOException {
        String body = this.render(request, "/static/js/pages/list.js", this.itemServerApi);
        model.addAttribute("title", "List");
        model.addAttribute("body", body);
        return "frame";
    }
}
