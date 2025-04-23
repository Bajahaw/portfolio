package tech.radhi.portfolio.content;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<String> addContent(@RequestBody ContentTemplate content) {
        service.addContent(content);
        return ResponseEntity.ok("added!");
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<String> updateContent(@PathVariable String id, @RequestBody ContentTemplate content){
        service.updateContent(id, content);
        return ResponseEntity.ok("updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContent(@PathVariable String id) {
        service.deleteContent(id);
        return ResponseEntity.ok("deleted!");
    }

}
