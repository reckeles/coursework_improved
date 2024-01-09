package org.coursework.api.model.project;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class ProjectExtended extends Project {
    private Integer id;
    private Integer is_active;
    private String token;
    private Long last_modified;
    private Integer is_public;
    private Integer is_private;
    private Integer priority_default;
    private Integer priority_start;
    private Integer priority_end;
    private String email;
    private String predefined_email_subjects;
    private Integer per_swimlane_task_limits;
    private Integer task_limit;
    private Integer enable_global_tags;
    private ProjectURL url;
}
