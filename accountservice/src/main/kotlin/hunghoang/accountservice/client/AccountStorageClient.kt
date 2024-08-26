package hunghoang.accountservice.client

import hunghoang.accountservice.model.Account
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class AccountStorageClient(private val restTemplate: RestTemplate) {
    companion object {
        @Value("\${accountstorage.url}")
        private lateinit var URL: String
    }

    fun listAccounts(): List<Account> {
        val response = restTemplate.getForEntity("$URL/list", Array<Account>::class.java)
        return response.body?.toList() ?: listOf()
    }
}