package hunghoang.accountstorage.service

import hunghoang.accountstorage.model.AccountEvent
import hunghoang.accountstorage.model.EventType
import hunghoang.accountstorage.service.accountmanager.AccountManagerService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class AccountEventConsumer(private val accountManagerService: AccountManagerService) {

    companion object {
        val LOG = LoggerFactory.getLogger(AccountEventConsumer::class.java)
    }

    @KafkaListener(id = "accountGroup", topics = ["account.event"])
    fun accountConsumer(accountEvent: AccountEvent) {
        LOG.info("$accountEvent")
        val account = accountEvent.account

        when (accountEvent.eventType) {
            EventType.CREATE -> {
                if (accountManagerService.save(account)) {
                    LOG.info("Account created successfully: $account")
                } else {
                    LOG.error("Failed to create account: $account")
                }
            }
            EventType.UPDATE -> {
                if (accountManagerService.update(account)) {
                    LOG.info("Account updated successfully: $account")
                } else {
                    LOG.error("Failed to update account: $account")
                }
            }
            EventType.DELETE -> {
                LOG.info("Account delete event received: $account")
            }
            else -> {
                LOG.warn("Unknown event type: ${accountEvent.eventType}")
            }
        }
    }
}