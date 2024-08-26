package hunghoang.accountstorage.service.accountmanager

import hunghoang.accountstorage.model.Account

interface AccountManagerService {
    fun save(account: Account): Boolean

    fun update(account: Account): Boolean

    fun delete(account: Account): Boolean

    fun list(): List<Account>
}