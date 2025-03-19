package tech.radhi.portfolio;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends CrudRepository<ContentTemplate, String> {
    List<ContentTemplate> getAllByType(String type);
    ContentTemplate getContentById(String id);
}
