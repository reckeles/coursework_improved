package org.coursework.api.model.user;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserExtended extends User {
    private Integer id;
    private Integer is_ldap_user;
    private String google_id;
    private String github_id;
    private Integer notifications_enabled;
    private String timezone;
    private String language;
    private Integer disable_login_form;
    private Integer twofactor_activated;
    private String twofactor_secret;
    private String token;
    private Integer notifications_filter;
    private Integer nb_failed_login;
    private Integer lock_expiration_date;
    private Integer gitlab_id;
    private Integer is_active;
    private String avatar_path;
    private String api_access_token;
    private String filter;
    private String theme;
}
