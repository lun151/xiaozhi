package com.lun.assistant;


import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;


import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @Description
 */
@AiService(
        wiringMode = EXPLICIT,
//        chatModel = "qwenChatModel",
        streamingChatModel = "qwenStreamingChatModel", //使用流式模型
        chatMemoryProvider = "chatMemoryProviderXiaozhi",
        tools = "appointmentTools",
        contentRetriever = "contentRetrieverPinecone"
)
public interface XiaoZhiAssistant {

    @SystemMessage(fromResource = "XiaoZhi-Promt.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
