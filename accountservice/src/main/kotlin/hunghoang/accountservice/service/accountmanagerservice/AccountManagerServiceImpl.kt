package hunghoang.accountservice.service.accountmanagerservice

import hunghoang.accountservice.client.AccountStorageClient
import hunghoang.accountservice.model.Account
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Service
class AccountManagerServiceImpl(
    private val accountStorageClient: AccountStorageClient
): AccountManagerService {

    companion object {
        private val RELOAD_DURATION = Duration.ofMinutes(2).toMillis()
    }

    private val executor = Executors.newSingleThreadScheduledExecutor()
    private val accountsMap = ConcurrentHashMap<String, Account>()

    init {
        loadRecords()
        reloadScheduled()
    }

    private fun reloadScheduled() {
        executor.scheduleAtFixedRate(
            {loadRecords()},
            RELOAD_DURATION,
            RELOAD_DURATION,
            TimeUnit.MILLISECONDS
        )
    }

    override fun list(): List<Account> {
        return accountsMap.values.toList()
    }

    override fun getById(id: String): Account {
        return accountsMap[id] ?: Account()
    }

    private fun loadRecords() {
        try {
            val accounts = accountStorageClient.listAccounts()
            val newAccountsMap = accounts.associateBy { it.id }
            accountsMap.clear()
            accountsMap.putAll(newAccountsMap)
        } catch (e: Exception) {
            println("Failed to refresh accounts: ${e.message}")
        }
    }
}