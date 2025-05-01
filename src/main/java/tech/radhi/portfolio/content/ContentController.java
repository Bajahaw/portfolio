package tech.radhi.portfolio.content;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public List<ContentTemplate> getAllContent() {
        return service.getAllContent();
    }

    @PostMapping("/save-all")
    public ResponseEntity<String> saveAll(@RequestBody List<ContentTemplate> list) {
        list.forEach(content -> service.saveContent(content)); // TODO: needs optimizing )_
        return ResponseEntity.ok("all saved");
    }

}
