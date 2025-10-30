package com.lun.java.ai.langchain4j;

import com.lun.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 */
@SpringBootTest
public class ToolsTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testTools() {
        String chat = separateChatAssistant.chat(1, "计算1+1");
        System.out.println(chat);
    }
}
