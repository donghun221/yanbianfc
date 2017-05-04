package com.ffyc.site.common.image.jackson;

import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.deser.CustomDeserializerFactory;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

/**
 * 自定义Jackson数据转换的对象配置
 * 
 * @author 宋翔
 * 
 */
public class CustomJacksonObjectMapper extends ObjectMapper
{

    public CustomJacksonObjectMapper()
    {
        CustomSerializerFactory factory = new CustomSerializerFactory();
        factory.addSpecificMapping(Date.class, new CustomJacksonDateSerializer());
        factory.addSpecificMapping(java.sql.Date.class, new CustomJacksonDateSerializer());
        factory.addSpecificMapping(Timestamp.class, new CustomJacksonDateTimeSerializer());
        CustomDeserializerFactory factory2 = new CustomDeserializerFactory();
        factory2.addSpecificMapping(Timestamp.class, new CustomJacksonTimestampDeserializer());
        //factory2.addSpecificMapping(String.class, new CustomJacksonStringDeserializer());
        this.setSerializerFactory(factory);
    }
}