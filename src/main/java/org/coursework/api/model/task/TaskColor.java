package org.coursework.api.model.task;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskColor {
    private String name;
    private String background;
    private String border;
}
