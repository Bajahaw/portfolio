package tech.radhi.portfolio.content;

import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {
    ContentRepository repository;
    JdbcAggregateTemplate jdbcTemplate;

    public ContentService(ContentRepository repository, JdbcAggregateTemplate template) {
        this.repository = repository;
        this.jdbcTemplate = template;
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

    public void addContent(ContentTemplate content) {
        jdbcTemplate.insert(content);
    }

    public void updateContent(String id, ContentTemplate content) {
        var c = new ContentTemplate(
                id, content.type(),
                content.contentBody()
        );
        repository.save(c);
    }

    public void deleteContent(String id) {
        repository.deleteById(id);
    }
}
