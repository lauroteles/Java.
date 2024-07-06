package lauroproject.example.agregadordeinvestimentos.service;

import lauroproject.example.agregadordeinvestimentos.controller.CreateUserDto;
import lauroproject.example.agregadordeinvestimentos.entity.User;
import lauroproject.example.agregadordeinvestimentos.repository.UserRepositoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepositoryRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Nested
    class createUser {

        @Test
        @DisplayName("Should Create User")
        void ShouldCreateUser() {

            var user = new User(
                    UUID.randomUUID(),
                    "username",
                    "email@2email.com",
                    "password",
                    Instant.now(),
                    null
            );

            doReturn(null).when(userRepository).save(any());
            var input = new CreateUserDto("username",
                                                "email@email",
                                                "123");

            var output = userService.createUser(input);

            assertNotNull(output);

        }

        @Test
        @DisplayName("Shoud throw exception when error occurs")
        void SholdTrhowExceptionWhenErrorOcurrs() {

                doThrow(new RuntimeException()).when(userRepository).save(any());
                var input = new CreateUserDto("username",
                        "email@email",
                        "123");

                assertThrows(RuntimeException.class,() -> userService.createUser(input));


        }

    }

}