package hunghoang.accountstorage.service

import hunghoang.accountstorage.model.AccountEvent
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener

class AccountEventConsumer {

    companion object {
        val LOG = LoggerFactory.getLogger(AccountEventConsumer::class.java)
    }

    @KafkaListener(id = "accountGroup", topics = ["account.event"])
    fun accountConsumer(accountEvent: AccountEvent) {
        LOG.info("$accountEvent")

    }
}