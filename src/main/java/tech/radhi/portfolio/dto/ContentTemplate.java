package tech.radhi.portfolio.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("CONTENT")
public record ContentTemplate(
        @Id
        @Column("ID")
        String id,

        @Column("CONTENT_TYPE")
        String type,

        @Column("CONTENT_BODY")
        String contentBody
) {}