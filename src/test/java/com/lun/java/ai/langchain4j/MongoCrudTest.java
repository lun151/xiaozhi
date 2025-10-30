package com.lun.java.ai.langchain4j;


import com.lun.bean.ChatMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @Description
 */
@SpringBootTest
public class MongoCrudTest {

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Test
//    public void testInsert() {
//        ChatMessages testInsert = new ChatMessages(1L, "testInsert");
//        mongoTemplate.insert(testInsert);
//    }

    @Test
    public void testInsert() {
        ChatMessages chatMessages = new ChatMessages();
        chatMessages.setContent("聊天记录列表");
        mongoTemplate.insert(chatMessages);
    }

    @Test
    public void testFind() {
        ChatMessages chatMessages = mongoTemplate.findById("68f9d719a5a0e9120c3ae31f", ChatMessages.class);
        System.out.println(chatMessages);
    }

    @Test
    public void testUpdate() {
        //如果id存在则更新，不存在则插入
        Criteria criteria = Criteria.where("_id").is("68f9d719a5a0e9120c3ae31f");
        Query query = new Query(criteria);
        Update update = new Update().set("content", "更新后的内容");
        mongoTemplate.upsert(query,update,ChatMessages.class);
    }

    @Test
    public void testDelete() {
        Criteria criteria = Criteria.where("_id").is("68f9d719a5a0e9120c3ae31f");
        Query query = new Query(criteria);
        mongoTemplate.remove(query,ChatMessages.class);
    }
}
