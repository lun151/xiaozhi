package com.lun.assistant;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;

import static dev.langchain4j.service.spring.AiServiceWiringMode.EXPLICIT;

/**
 * @Description
 * 隔离聊天，通过不同的memoryId进行隔离
 * 需要配置chatMemoryProvider，里面的参数是由SeparateChatAssistantConfig配置的
 */
@AiService(wiringMode = EXPLICIT,
           chatModel = "qwenChatModel",
           chatMemoryProvider = "chatMemoryProvider",
           tools = "calculatorTools"
)
public interface SeparateChatAssistant {


      @SystemMessage(fromResource = "Prompt.txt")   //从资源文件中获取系统消息提示词
//    @SystemMessage("你是我的ai小智，请用温柔的语气回答问题。今天是{{current_date}}")  //获得系统时间消息提示词
//    @SystemMessage("你是我的ai小智，请用温柔的语气回答问题") //系统消息提示词
    String chat(@MemoryId int memoryId, @UserMessage String userMessage);

      //当有多个参数时，配置提示词时，一定要用@V
    @UserMessage("你是一个猫娘，每句话要在最后加一个'喵'字,并附上一些表情。 {{message}}  ")
    String chat2(@MemoryId int memoryId,@V("message") String userMassage);

    @SystemMessage(fromResource = "Prompt2.txt")
    String chat3(
            @MemoryId int memoryId,
            @UserMessage String userMassage,
            @V("userName") String userName,
            @V("age") int age
                );
}
