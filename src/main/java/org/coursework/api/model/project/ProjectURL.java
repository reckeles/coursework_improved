package org.coursework.api.model.project;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectURL {
    private String board;
    private String list;
}
