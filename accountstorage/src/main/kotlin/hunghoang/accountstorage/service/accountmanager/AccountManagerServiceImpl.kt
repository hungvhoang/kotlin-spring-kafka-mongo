package hunghoang.accountstorage.service.accountmanager

import hunghoang.accountstorage.model.Account
import hunghoang.accountstorage.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountManagerServiceImpl(
    private val accountRepository: AccountRepository,
    ) : AccountManagerService {

    override fun save(account: Account): Boolean {
        return try {
            accountRepository.save(account)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun update(account: Account): Boolean {
        return if (accountRepository.existsById(account.id)) {
            try {
                accountRepository.save(account.copy(updatedTimestamp = System.currentTimeMillis()))
                true
            } catch (e: Exception) {
                false
            }
        } else {
            false
        }
    }

    override fun delete(account: Account): Boolean {
        return if (accountRepository.existsById(account.id)) {
            try {
                accountRepository.deleteById(account.id)
                true
            } catch (e: Exception) {
                false
            }
        } else {
            false
        }
    }

    override fun list(): List<Account> {
        return accountRepository.findAll()
    }

}