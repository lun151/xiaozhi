package com.lun.java.ai.langchain4j;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;

/**
 * @Description
 */
@SpringBootTest
public class LLMTest {

    @Test
    public void testDemo() {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();
        //向模型提问
        String answer = model.chat("你好");
        //输出结果
        System.out.println(answer);

    }

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @Test
    public void testSpringBoot() {
        //向模型提问
        String answer = openAiChatModel.chat("你是谁,你是什么版本？");
        //输出结果
        System.out.println(answer);
    }

    /**
     * 测试dashscope qwen
     */
    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testDashScopeQwen() {
        //向模型提问
        String answer = qwenChatModel.chat("你好,你是谁");
        //输出结果
        System.out.println(answer);
    }

    @Test
    public void testImage() {
        WanxImageModel wanxImageModel = WanxImageModel.builder()
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .modelName("wan2.5-t2i-preview")
                .build();
        Response<Image> imageResponse = wanxImageModel.generate("一个二次元蓝发少女");
        URI url = imageResponse.content().url();
        System.out.println( url);
    }
}
