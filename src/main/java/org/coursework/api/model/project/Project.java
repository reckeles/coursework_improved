package org.coursework.api.model.project;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Project {
    @Builder.Default
    private String name = null;
    @Builder.Default
    private String description = null;
    @Builder.Default
    private Integer owner_id = null;
    @Builder.Default
    private String identifier = null;
    @Builder.Default
    private String start_date = null;
    @Builder.Default
    private String end_date = null;
    @Builder.Default
    private Integer id = null;
}
