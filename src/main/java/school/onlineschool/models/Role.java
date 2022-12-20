package school.onlineschool.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, USER, STUDENT, TEACHER;

    @Override
    public String getAuthority() {
        return name();
    }
}

