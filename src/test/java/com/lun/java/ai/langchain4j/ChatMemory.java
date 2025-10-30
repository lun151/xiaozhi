package com.lun.java.ai.langchain4j;

import com.lun.assistant.Assistant;
import com.lun.assistant.MemoryAssistant;
import com.lun.assistant.SeparateChatAssistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.service.spring.AiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 */
@SpringBootTest
public class ChatMemory {

    @Autowired
    private QwenChatModel qwenChatModel;
    @Autowired
    private MemoryAssistant memoryAssistant;

    @Test
    public void testChatMemory() {
        //创建Memory，设置最大记忆数量
        MessageWindowChatMemory messageWindowChatMemory = MessageWindowChatMemory.withMaxMessages(10);

        Assistant assistant = AiServices
                .builder(Assistant.class)
                .chatMemory(messageWindowChatMemory)   //配置 Memory
                .chatLanguageModel(qwenChatModel)
                .build();

        String chat = assistant.chat("我是苦学langchain4j的程序员");
        System.out.println(chat);
        String chat1 = assistant.chat("我是谁");
        System.out.println(chat1);

    }

    @Test
    public void testMemoryAssistant() {
        String chat = memoryAssistant.chat("我是java开发程序员 lun ");
        System.out.println(chat);
        String chat1 = memoryAssistant.chat("我是谁");
        System.out.println(chat1);
    }


    @Autowired
    private SeparateChatAssistant separateChatAssistant;
    @Test
    public void testSeparateChatAssistant() {
        String chat = separateChatAssistant.chat(1,"我是java开发程序员 lun ");
        System.out.println(chat);
        String chat1 = separateChatAssistant.chat(1,"我是谁");
        System.out.println(chat1);
        String chat2 = separateChatAssistant.chat(2,"我是java开发程序员 jack ");
        System.out.println(chat2);
        String chat3 = separateChatAssistant.chat(2,"我是谁");
        System.out.println(chat3);
    }
}
