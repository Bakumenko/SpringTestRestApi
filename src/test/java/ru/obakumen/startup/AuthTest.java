package ru.obakumen.startup;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.obakumen.startup.models.Role;
import ru.obakumen.startup.models.User;
import ru.obakumen.startup.repositories.RolesRepository;
import ru.obakumen.startup.repositories.UsersRepository;
import ru.obakumen.startup.security.jwt.JwtProvider;
import ru.obakumen.startup.services.UsersService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {
    @Autowired
    @MockBean
    private RolesRepository rolesRepository;

    @Autowired
    @MockBean
    private UsersRepository usersRepository;

    @Autowired
    private MockMvc mockMvc;

    private String testRoleName = "ROLE_ADMIN";
    private String testUserUsername = "test_user";
    private String testUserPassword = "test";
    private String testUserFirstName = "TestName";
    private String testUserLastName = "TestLastName";
    private int testAge = 30;

    @Before
    public void setUp() {
        Role testRole = rolesRepository.findRoleByName(testRoleName);
        User testUser = new User(testUserUsername, testUserPassword,
                testUserFirstName, testUserLastName, testAge, testRole);

        Mockito.when(usersRepository.findUserByUsername(Mockito.any())).thenReturn(testUser);
    }


    @Test
    public void getUserTest() throws Exception {
        User user = usersRepository.findUserByUsername(testUserUsername);
        //String token = jwtProvider.generateToken(testUser.getUsername());
        mockMvc.perform(get("/admin/users/{test_user_username}", user.getUsername())
      //          .header("Authorization", "Bearer " + token)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(testUserUsername))
                .andExpect(jsonPath("$.age").value(testAge));
    }

    @Test
    public void greetingTest() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, let's go /auth for login!")));
    }
}
