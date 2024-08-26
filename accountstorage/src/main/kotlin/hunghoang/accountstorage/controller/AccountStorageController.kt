package hunghoang.accountstorage.controller

import hunghoang.accountstorage.model.Account
import hunghoang.accountstorage.service.accountmanager.AccountManagerService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/account-storage")
class AccountStorageController(
    private val accountManagerService: AccountManagerService
) {

    @GetMapping("/list")
    fun listAccounts(): ResponseEntity<List<Account>> {
        val accounts = accountManagerService.list()
        return ResponseEntity.ok(accounts)
    }
}