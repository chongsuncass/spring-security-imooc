package com.imooc;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
public class FileControllerTest extends BaseTest {

    @Test
    public void testFileUpload() {
        try {
            String result = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file/upload")
                                  .file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"))))
                                  .andExpect(MockMvcResultMatchers.status().isOk())
                                   .andReturn().getResponse().getContentAsString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
