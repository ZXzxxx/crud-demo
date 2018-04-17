package com.zx.cruddemo.jsonDeserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zx.cruddemo.controller.SchoolController;
import com.zx.cruddemo.domain.School;
import com.zx.cruddemo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;


import java.io.IOException;


public class SchoolDeserialize extends JsonDeserializer<School>{

    @Override
    public School deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode root = mapper.readTree(p);//读取到json

        Integer idNo = root.get("value").asInt();
        String description = null;

        if(root.get("label") != null){ //必须判断  否则会报json序列化的空指针错误
            description = root.get("label").asText();
        }

        if(idNo == 0){  //删除进这里
            return null;//没获取到id就返回空
        }else {  //添加和编辑进这里
            return new School(idNo, description);//有id就返回一个对象
        }
    }
}



