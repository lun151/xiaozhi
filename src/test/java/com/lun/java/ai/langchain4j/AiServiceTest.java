package com.lun.java.ai.langchain4j;

import com.lun.assistant.Assistant;
import dev.langchain4j.service.spring.AiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 */
@SpringBootTest
public class AiServiceTest {

    @Autowired
    private Assistant assistant;

    @Test
    public void testAiService () {
        String chat = assistant.chat("你是谁");
        System.out.println(chat);
    }
}
