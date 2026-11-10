package com.example.student.mapper;

import com.example.student.dto.user.UserResponse;
import com.example.student.dto.user.UserResponseList;
import com.example.student.model.Role;
import com.example.student.model.User;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UserMapperImplTest {

    private final UserMapperImpl mapper = new UserMapperImpl();

    private User createUser(long id, String email) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setPassword("pass");
        user.setRoles(Set.of(Role.ADMIN));
        return user;
    }

    @Test
    void toListResponse_shouldMapAllAndCount() {
        UserResponseList result = mapper.toListResponse(
                List.of(createUser(1L, "a@mail.ru"), createUser(2L, "b@mail.ru")));

        assertThat(result.getTotalCount()).isEqualTo(2);
        assertThat(result.getResponses())
                .extracting(UserResponse::getEmail)
                .containsExactly("a@mail.ru", "b@mail.ru");
    }
}
