package hunghoang.accountservice.controller

import hunghoang.accountservice.model.Account
import hunghoang.accountservice.model.AccountEvent
import hunghoang.accountservice.model.EventType
import hunghoang.accountservice.model.ResponseMessage
import hunghoang.accountservice.service.AccountEventProducer
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController(
    private val accountEventProducer: AccountEventProducer
) {

    @PostMapping("create")
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

}