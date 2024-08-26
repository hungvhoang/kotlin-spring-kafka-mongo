package hunghoang.accountservice.controller

import hunghoang.accountservice.model.Account
import hunghoang.accountservice.model.AccountEvent
import hunghoang.accountservice.model.EventType
import hunghoang.accountservice.model.ResponseMessage
import hunghoang.accountservice.service.AccountEventProducer
import hunghoang.accountservice.service.accountmanagerservice.AccountManagerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/account")
class AccountController(
    private val accountManagerService: AccountManagerService,
    private val accountEventProducer: AccountEventProducer
) {

    @PostMapping("/create")
    fun createAccount(@RequestBody account: Account): ResponseEntity<ResponseMessage> {
        return try {
            val accountEvent = AccountEvent(
                account = account,
                eventType = EventType.CREATE,
                timestamp = System.currentTimeMillis()
            )
            accountEventProducer.publish(accountEvent)
            ResponseEntity.ok(
                ResponseMessage(
                success = true,
                message = "createAccount success",
                data = null
            ))
        } catch (e: Exception) {
            ResponseEntity.ok(
                ResponseMessage(
                success = false,
                message = e.message ?: "",
                data = null
            ))
        }
    }

    @PostMapping("/update")
    fun update(@RequestBody account: Account): ResponseEntity<ResponseMessage> {
        return try {
            val accountEvent = AccountEvent(
                account = account,
                eventType = EventType.UPDATE,
                timestamp = System.currentTimeMillis()
            )
            accountEventProducer.publish(accountEvent)
            ResponseEntity.ok(
                ResponseMessage(
                    success = true,
                    message = "update success",
                    data = null
                ))
        } catch (e: Exception) {
            ResponseEntity.ok(
                ResponseMessage(
                    success = false,
                    message = e.message ?: "",
                    data = null
                ))
        }
    }

    @GetMapping("/list")
    fun list(): ResponseEntity<ResponseMessage> {
        return try {
            val accounts = accountManagerService.list()
            ResponseEntity.ok(ResponseMessage(
                success = true,
                message = "Accounts retrieved successfully",
                data = accounts))
        } catch (e: Exception) {
            ResponseEntity.status(500)
                .body(ResponseMessage(
                    success = false,
                    message = "Failed to retrieve accounts"
                ))
        }
    }

    @GetMapping("/get/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<ResponseMessage> {
        return try {
            val account = accountManagerService.getById(id)
            if (account != null) {
                ResponseEntity.ok(ResponseMessage(
                    success = true,
                    message = "Account retrieved successfully",
                    data = account))
            } else {
                ResponseEntity.status(404).body(ResponseMessage(
                    success = false,
                    message = "Account not found"
                ))
            }
        } catch (e: Exception) {
            ResponseEntity.status(500).body(ResponseMessage(
                success = false,
                message = "Failed to retrieve account"
            ))
        }
    }
}