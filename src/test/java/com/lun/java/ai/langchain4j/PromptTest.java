package com.lun.java.ai.langchain4j;

import com.lun.assistant.MemoryAssistant;
import com.lun.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 */
@SpringBootTest
public class PromptTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testPrompt(){
        String chat = separateChatAssistant.chat2(10,"最近有点emo，将个笑话给我听吧");
        System.out.println(chat);

        String chat1 = separateChatAssistant.chat2(10,"你是个笨蛋喵  ");
        System.out.println(chat1);
    }


    @Autowired
    private MemoryAssistant  memoryAssistant;
    @Test
    public void testMemoryAssistant(){
        String chat = memoryAssistant.chat("你是谁,你是哪个语言模型");
        System.out.println(chat);

//        String chat1 = memoryAssistant.chat("你会讲笑话吗");
//        System.out.println(chat1);
//        String chat2 = memoryAssistant.chat("你是个小笨蛋");
//        System.out.println(chat2);

    }

    @Test
    public void testSeparateChatAssistant(){
        String userName = "lun";
        int age = 18;
        String chat = separateChatAssistant.chat3(11,"你是谁,你是哪个语言模型",userName,age);
        System.out.println(chat);

        String chat1 = separateChatAssistant.chat3(11,"你会讲笑话吗",userName,age);
        System.out.println(chat1);
        String chat2 = separateChatAssistant.chat3(11,"你是个小笨蛋",userName,age);
        System.out.println(chat2);
    }
}
