package com.imooc;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
public class UserControllerTest extends BaseTest {

    @Test
    public void testQueryUser() {
       try {
           mockMvc.perform(MockMvcRequestBuilders.get("/user")
                           .contentType(MediaType.APPLICATION_JSON_UTF8))
                           .andExpect(MockMvcResultMatchers.status().isOk())
                           .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
        } catch (Exception e) {
            e.printStackTrace();
       }
    }
}
