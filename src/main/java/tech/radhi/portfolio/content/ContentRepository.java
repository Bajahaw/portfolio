package tech.radhi.portfolio.content;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import tech.radhi.portfolio.dto.ContentTemplate;

import java.util.List;

@Repository
public interface ContentRepository extends CrudRepository<ContentTemplate, String> {

    @Query("SELECT * FROM \"CONTENT\"")
    List<ContentTemplate> getAllContent();

    List<ContentTemplate> getAllByType(String type);

    ContentTemplate getContentById(String id);

    void deleteById(@NonNull String id);
}
