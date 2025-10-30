package com.lun.assistant;

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @Description
 *  如果配置了多个模型，则需要指定wiringMode = EXPLICIT 和 模型的名称
 */

@AiService(wiringMode =EXPLICIT,
        chatModel = "qwenChatModel")
public interface Assistant {
    String chat(String userInput);
}
