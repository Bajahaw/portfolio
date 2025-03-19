package tech.radhi.portfolio.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentController {

    ContentService service;

    public ContentController(ContentService service) {
        this.service = service;
    }

    @ResponseBody
    @GetMapping("/{id}")
    public String content(@PathVariable String id) {
        return service.getContentById(id);
    }
}
