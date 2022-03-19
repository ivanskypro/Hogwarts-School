package ru.hogwarts.hogwartsschool;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.hogwarts.hogwartsschool.controller.FacultyController;
import ru.hogwarts.hogwartsschool.model.Faculty;
import ru.hogwarts.hogwartsschool.repositories.FacultyRepository;
import ru.hogwarts.hogwartsschool.service.FacultyService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerTests {

    private Faculty faculty;

    private List<Faculty> facultyList;

    private JSONObject facultyObject;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @BeforeEach
    void initDataForTest() throws JSONException {
        faculty = new Faculty();
        faculty.setId(1L);
        faculty.setName("Unknown Faculty1");
        faculty.setColor("yellow");

        Faculty faculty1 = new Faculty();
        faculty1.setId(2L);
        faculty1.setName("Unknown Faculty2");
        faculty1.setColor("red");

        Faculty faculty2 = new Faculty();
        faculty2.setId(3L);
        faculty2.setName("Unknown Faculty3");
        faculty2.setColor("blue");

        facultyList = List.of(
                faculty,
                faculty1,
                faculty2
        );


        facultyObject = new JSONObject();
        facultyObject.put("name", "Unknown Faculty1");
        facultyObject.put("color", "yellow");


    }

    @Test
    void createFacultyTest() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Unknown Faculty1"))
                .andExpect(jsonPath("$.color").value("yellow"));


    }

    @Test
    void getFacultyByIdTest() throws Exception {
        when(facultyRepository.findById(anyLong())).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Unknown Faculty1"))
                .andExpect(jsonPath("$.color").value("yellow"));

    }

    @Test
    void updateFacultyTest() throws Exception {
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Unknown Faculty1"))
                .andExpect(jsonPath("$.color").value("yellow"));
    }

    @Test
    void deleteFacultyTest() throws Exception {
        doNothing().when(facultyRepository).deleteById(anyLong());

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + 1L))
                .andExpect(status().isOk());
    }

    @Test
    void findFacultyByColor() throws Exception {
        when(facultyRepository.findByColorIgnoreCase("yellow")).thenReturn((List<Faculty>) facultyList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/?color=" + "yellow")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void findFacultyByNameAndColor() throws Exception {
        when(facultyRepository.findByColorIgnoreCase(anyString(), anyString())).thenReturn((List<Faculty>) facultyList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/params/?color=" + "yellow" + "&name=" + "Unknown Faculty1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].name").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].color").isNotEmpty());
    }

}
