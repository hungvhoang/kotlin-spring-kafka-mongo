package hunghoang.accountservice.service.accountmanagerservice

import hunghoang.accountservice.model.Account

interface AccountManagerService {
    fun list(): List<Account>

    fun getById(id: String): Account
}