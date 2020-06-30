package br.com.springboot.demo;

import br.com.springboot.demo.domain.AlterarPessoaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PessoaControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Teste 1")
    public void testeRequests_1() throws Exception {
        int i = new Random().nextInt();
        AlterarPessoaRequest request = new AlterarPessoaRequest();
        request.nome = "Fulano " + i;
        request.idade = i;

        MvcResult result =
                mockMvc.perform(put("/pessoa/" + i)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk())
                        .andReturn();

        assert result.getResponse() != null;
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
