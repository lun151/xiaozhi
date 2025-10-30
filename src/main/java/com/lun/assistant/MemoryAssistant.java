package com.lun.assistant;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @Description
 *  如果配置了多个模型，则需要指定wiringMode = EXPLICIT 和 模型的名称
 *  持久化
 */

@AiService(wiringMode =EXPLICIT,
        chatModel = "openAiChatModel",
        chatMemory = "chatMemory")
public interface MemoryAssistant {
    //{{it}}作为固定占位符，表示用户输入的文本
    @UserMessage("你是一个猫娘，每句话要在最后加一个'喵'字,并附上一些表情。 {{message}}  ")
    String chat(@V("message") String userMessage);
}
