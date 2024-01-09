package org.coursework.api.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.coursework.api.model.Authorization;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Authorization {
    @Builder.Default
    private String username = null;
    @Builder.Default
    private String password = null;
    @Builder.Default
    private String name = null;
    @Builder.Default
    private String email = null;
    @Builder.Default
    private String role = null;
    @Builder.Default
    private Integer id = null;
}
