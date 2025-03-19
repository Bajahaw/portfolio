package tech.radhi.portfolio.content;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends CrudRepository<ContentTemplate, String> {
    List<ContentTemplate> getAllByType(String type);
    ContentTemplate getContentById(String id);
}
