package com.lun.bean;

import lombok.Data;

/**
 * @Description
 */
@Data
public class ChatForm {

    private Long memoryId;  //对话id

    private String message; //用户输入的文本

}
