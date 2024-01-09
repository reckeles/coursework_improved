package org.coursework.api.model.task;

import lombok.Builder;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
public class TaskExtended extends Task {
    private Integer id;
    private Long date_creation;
    private Long date_completed;
    private Integer position;
    private Integer is_active;
    private Long date_modification;
    private Float time_spent;
    private Float time_estimated;
    private Long date_moved;
    private Integer recurrence_parent;
    private Integer recurrence_child;
    private String external_provider;
    private String external_uri;
    private String url;
    private TaskColor color;
}
