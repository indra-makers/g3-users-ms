package com.co.indra.coinmarketcap.users.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

	@Value("${spring.redis.end.point}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.user}")
	private String user;

	@Value("${spring.redis.pass}")
	private String password;

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
				.connectTimeout(Duration.ofSeconds(30)).usePooling().build();


		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host);
		config.setUsername(password);

		return new JedisConnectionFactory(config, jedisClientConfiguration);

	}
	
	 @Bean
    @Scope("singleton")
    public RedisConnection getRedisConnection(JedisConnectionFactory connectionFActory) {
        return connectionFActory.getConnection();
    }

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new StringRedisSerializer());
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	private RedisCacheConfiguration redisCacheConfiguration() {
		RedisSerializationContext.SerializationPair<Object> serializationPair = RedisSerializationContext.SerializationPair
				.fromSerializer(new JdkSerializationRedisSerializer());

		return RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(serializationPair)
				.disableCachingNullValues();
	}

	private RedisCacheManager getCacheManagerByTtl(Long ttl) {
		RedisCacheConfiguration configuration = this.redisCacheConfiguration().entryTtl(Duration.ofSeconds(ttl));

		return RedisCacheManager.builder(this.jedisConnectionFactory()).cacheDefaults(configuration).build();
	}

	@Bean("expire30Mins")
	@Primary
	public CacheManager get30MinuteCache() {
		return getCacheManagerByTtl(1800l);
	}
}
