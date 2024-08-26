package hunghoang.accountservice.config

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


@Configuration
class KafkaConfig {

    @Value("\${spring.kafka.topic.account-event}")
    private lateinit var accountEventTopicName: String

    @Bean
    fun accountTopic(): NewTopic {
        return NewTopic(accountEventTopicName, 2, 1.toShort())
    }

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}