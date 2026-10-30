package kz.omargali.studentapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StudentApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void infoShowsStudentName() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.student").value("Ruslan Omargali"));
    }

    @Test
    void createAndGetStudent() throws Exception {
        String json = """
                {"firstName":"Ruslan","lastName":"Omargali","email":"ruslan.omargali@example.com","course":2}
                """;
        mockMvc.perform(post("/api/students").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.lastName").value("Omargali"));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Ruslan"));
    }

    @Test
    void nonexistentStudentReturns404() throws Exception {
        mockMvc.perform(get("/api/students/99999"))
                .andExpect(status().isNotFound());
    }
}
