package hunghoang.accountservice.service

import hunghoang.accountservice.model.AccountEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class AccountEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, AccountEvent>,
    @Value("\${spring.kafka.topic.account-event}")
    private val accountEventTopicName: String,

) {
    fun publish(accountEvent: AccountEvent) {
        kafkaTemplate.send(accountEventTopicName, accountEvent)
    }
}