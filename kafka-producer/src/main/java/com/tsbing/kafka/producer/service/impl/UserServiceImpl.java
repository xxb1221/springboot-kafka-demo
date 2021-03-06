package com.tsbing.kafka.producer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tsbing.kafka.common.User;
import com.tsbing.kafka.producer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Value("${spring.kafka.topic.userTopic}")
    private String userTopic;

    @Autowired
    KafkaTemplate kafkaTemplate;

    /**
     * 发送用户信息
     *
     * @return
     */
    @Override
    public Boolean sendUserMsg() {
        User user = new User();
        user.setId(1);
        user.setUsername("test-usernam");
        user.setPassword("test-password");
        kafkaTemplate.send(userTopic, JSONObject.toJSONString(user));
        log.info("已发送");
        return Boolean.TRUE;
    }
}
