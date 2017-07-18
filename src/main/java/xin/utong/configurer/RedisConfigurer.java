package xin.utong.configurer;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * redis config
 * Created by yutong on 2017/7/17.
 */
@Configuration
public class RedisConfigurer {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(new RedisSerializer<Object>() {
            @Override
            public byte[] serialize(Object o) throws SerializationException {
                return JSONObject.toJSONBytes(o, SerializerFeature.WriteMapNullValue);
            }

            @Override
            public Object deserialize(byte[] bytes) throws SerializationException {
                return JSONObject.parse(bytes);
            }
        });
        return redisTemplate;
    }

    @Bean
    public MessageListenerAdapter messageListenerAdapter() {
        return new MessageListenerAdapter((MessageListener) (message, pattern) -> {
            xin.utong.model.Message msg = JSONObject.parseObject(message.getBody(), xin.utong.model.Message.class);
            System.out.println("channel:" + new String(message.getChannel())
                    + ", message:" + msg);
        });
    }

    @Bean
    public RedisMessageListenerContainer redisContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisContainer = new RedisMessageListenerContainer();
        redisContainer.setConnectionFactory(redisConnectionFactory);
        redisContainer.addMessageListener(messageListenerAdapter(), new ChannelTopic("hello-channel2"));
        return redisContainer;
    }
}
