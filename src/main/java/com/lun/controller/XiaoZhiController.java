package com.lun.controller;

import com.lun.assistant.XiaoZhiAssistant;
import com.lun.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Description
 */
@Tag(name = "小智")
@RestController
@RequestMapping("/xiaozhi")
public class XiaoZhiController {

    @Autowired
    private XiaoZhiAssistant xiaoZhiAssistant;

    @Operation(summary = "聊天") //当项目集成 Swagger UI 时，这个注解会使得 /xiaozhi/chat 接口在文档界面中显示为"聊天"
    @PostMapping(value = "/chat",produces = "text/stream;charset=utf-8") //设置成流式输出，输出的数据为UTF-8编码
    public Flux<String> chat(@RequestBody ChatForm chatForm){
        return xiaoZhiAssistant.chat(chatForm.getMemoryId(),chatForm.getMessage());
    }

}
