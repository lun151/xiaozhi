package com.lun.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("chat_messages")  //自动在配置好的数据库中创建名字"chat_messages"为集合
public class ChatMessages {
    //唯一标识，映射到 mongodb中的_id字段
    @Id
    private ObjectId messageId;

    private String memoryId;

    private String content; //存储聊天记录列表的json字符串
}
