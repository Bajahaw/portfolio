package tech.radhi.portfolio.content;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.radhi.portfolio.dto.ContentTemplate;
import tech.radhi.portfolio.dto.ProjectTemplate;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    private static final Logger log = LoggerFactory.getLogger(ContentController.class);
    private final ContentService service;

    public ContentController(ContentService service) {
        this.service = service;
    }

    @CacheEvict("default")
    @GetMapping("/invalidate-cache")
    public ResponseEntity<String> invalidateCache() {
        log.warn("Cache cleared!");
        return ResponseEntity.ok("Cache Invalidated!");
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

    @PostMapping("/project/save")
    public ResponseEntity<String> saveProject(
            @RequestParam("id") String id,
            @RequestBody ProjectTemplate project
    ) {
        if (service.saveProject(id, project))
            return ResponseEntity.ok("added!");

        return ResponseEntity
                .status(400)
                .body("Unable to save project!");
    }

    @GetMapping("/download-cv")
    public ResponseEntity<Void> prepareCV() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("HX-Redirect", "/content/cv");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @GetMapping("/cv")
    public ResponseEntity<Resource> downloadCV() throws IOException {
        String url = service.getContentById("cv-url");
        Resource pdf = new UrlResource(url);
        if (!pdf.exists() || !pdf.isReadable()) {
            log.error("resource can not be downloaded");
            return ResponseEntity.notFound().build();
        }
        String filename = "CV-NEW-V3.pdf";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(pdf);
    }

}
