package org.coursework.api.model.task;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task {
    @Builder.Default
    private String title = null;
    @Builder.Default
    private Integer project_id = null;
    @Builder.Default
    private String color_id = null;
    @Builder.Default
    private Integer column_id = null;
    @Builder.Default
    private Integer owner_id = null;
    @Builder.Default
    private Integer creator_id = null;
    @Builder.Default
    private String date_due = null;
    @Builder.Default
    private String description = null;
    @Builder.Default
    private Integer category_id = null;
    @Builder.Default
    private Integer score = null;
    @Builder.Default
    private Integer swimlane_id = null;
    @Builder.Default
    private Integer priority = null;
    @Builder.Default
    private Integer recurrence_status = null;
    @Builder.Default
    private Integer recurrence_trigger = null;
    @Builder.Default
    private Integer recurrence_factor = null;
    @Builder.Default
    private Integer recurrence_timeframe = null;
    @Builder.Default
    private Integer recurrence_basedate = null;
    @Builder.Default
    private String reference = null;
    @Builder.Default
    private String[] tags = null;
    @Builder.Default
    private String date_started = null;
    @Builder.Default
    private Integer id = null;
}
