package br.com.wbs.modules.company.controllers;

import br.com.wbs.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.wbs.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CreateJobControllerTest {

    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void should_be_able_to_create_a_new_job() throws Exception {
        var createJobDTO = CreateJobDTO.builder()
                .description("DESCRIPTION_TEST")
                .benefits("BENEFITS_TEST")
                .level("LEVEL_TEST")
                        .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson(createJobDTO))
                .header("Authorization", TestUtils.generateToken
                        (UUID.fromString("9d074948-37cc-469d-bb8e-ce19c8113c00"), "JAVAGAS_@123#"))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        System.out.println(result);
    }
}
