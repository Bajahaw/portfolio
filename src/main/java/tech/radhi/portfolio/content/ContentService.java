package tech.radhi.portfolio.content;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    ContentRepository repository;

    public ContentService(ContentRepository repository) {
        this.repository = repository;
    }
    public String getContentById(String id) {
        ContentTemplate p = repository.getContentById(id);
        return p != null ? p.contentBody() : "Oh!, Something seems off, got nothing to say . . . ";
    }

    public List<ContentTemplate> getListOfContent(String type) {
        var list = repository.getAllByType(type);
        if (!list.isEmpty()) return list;
        var error = new ContentTemplate(
                "error", type,
                "Oh!, Something seems off, got nothing to show ..");
        return List.of(error);
    }

}
