package tech.radhi.portfolio.content;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content")
public class ContentController {

    ContentService service;

    public ContentController(ContentService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public String content(@PathVariable String id) {
        return service.getContentById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ContentTemplate content) {
        service.saveContent(content);
        return ResponseEntity.ok("added!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContent(@PathVariable String id) {
        service.deleteContent(id);
        return ResponseEntity.ok("deleted!");
    }

}
