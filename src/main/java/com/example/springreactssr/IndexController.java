package com.example.springreactssr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class IndexController extends AbstractController {
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) throws ScriptException, IOException {
        String body = this.render(request, "/static/js/pages/index.js", null);
        model.addAttribute("title", "Index");
        model.addAttribute("body", body);
        return "frame";
    }
}
