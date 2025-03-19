package tech.radhi.portfolio;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MainController {

    ContentRepository repository;

    public MainController(ContentRepository repository) {
        this.repository = repository;
    }

    @ResponseBody
    @GetMapping("/content/{overview}")
    public String overview(@PathVariable String overview) {
        ContentTemplate p = repository.getContentById(overview);
        return p != null ? p.contentBody() : "Oh!, Something seems off, got nothing to say . . . ";
    }

    @GetMapping("/about")
    public String about(Model model, @RequestHeader(value = "HX-Request", required = false) String hxRequest) {
        List<ContentTemplate> questions = repository.getAllByType("q");
        model.addAttribute("questions", questions);
        // headless in case of using htmx
        if ("true".equals(hxRequest)) {
            model.addAttribute("headless", true);
        }
        return "about";
    }

    @GetMapping("/")
    public String index(Model model, @RequestHeader(value = "HX-Request", required = false) String hxRequest) {
        if ("true".equals(hxRequest)) {
            model.addAttribute("headless", true);
        }
        return "index";
    }
}

