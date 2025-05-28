package tech.radhi.portfolio.content;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.stereotype.Service;
import tech.radhi.portfolio.dto.ContentTemplate;
import tech.radhi.portfolio.dto.ProjectTemplate;

import java.util.Comparator;
import java.util.List;

@Service
public class ContentService {
    private final ContentRepository repository;
    private final JdbcAggregateTemplate jdbcTemplate;
    private final ObjectMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(ContentService.class);

    public ContentService(ContentRepository repository, JdbcAggregateTemplate template, ObjectMapper mapper) {
        this.repository = repository;
        this.jdbcTemplate = template;
        this.mapper = mapper;
    }

    @Cacheable(cacheNames = "default", key = "#id")
    public String getContentById(String id) {
        ContentTemplate p = repository.getContentById(id);
        return p != null ? p.contentBody() : "Oh!, Something seems off, got nothing to say ..";
    }

    @Cacheable(cacheNames = "default", key = "#type")
    public List<ContentTemplate> getListOfContent(String type) {
        var list = repository.getAllByType(type);
        if (!list.isEmpty()) return list;
        var error = new ContentTemplate(
                "error", type,
                "Oh!, Something seems off, got nothing to show ..");
        return List.of(error);
    }

    @Cacheable(cacheNames = "default")
    public List<ProjectTemplate> getListOfProjects(){
        return getListOfContent("project")
                .stream()
                .map(this::toProject)
                .sorted(Comparator
                        .comparing(ProjectTemplate::year)
                        .reversed()
                ).toList();
    }

    public ProjectTemplate toProject(ContentTemplate contentTemplate) {
        try {
            return mapper.readValue(contentTemplate.contentBody(), ProjectTemplate.class);
        } catch (JsonProcessingException e) {
            log.error("Error parsing project:", e);
            return new ProjectTemplate(0,"error","error happened",List.of("error"),"error");
        }
    }

    public void saveContent(ContentTemplate content) {
        var contentExist = repository.existsById(content.id());
        if (contentExist) repository.save(content); // update
        else jdbcTemplate.insert(content);
    }

    public void deleteContent(String id) {
        repository.deleteById(id);
    }

    public List<ContentTemplate> getAllContent() {
        return repository.getAllContent();
    }
}
