package com.lun.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



/**
 * @Description
 */
@Component
public class CalculatorTools {

    @Tool(name = "加法",value = "返回两个数之和")
    double sum(
            @ToolMemoryId int memoryId,         //区分不同用户
            // 方法参数使用@P注解,注解有两个属性，value:描述信息，必填， required:表示该参数为是否必填参数，默认为true，为可选字段
            @P(value = "加数1", required = true) double a,
            @P(value = "加数2", required = true) double b)
    {
        System.out.println("调用加法运算" + memoryId);
        return a+b;
    }

    @Tool(name = "平方根",value = "返回一个数的平方根")
    double squareRoot(
            @ToolMemoryId int memoryId,
            double a){
        System.out.println("计算平方根");
        return Math.sqrt(a);
    }
}
