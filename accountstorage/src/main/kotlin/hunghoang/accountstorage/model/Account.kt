package hunghoang.accountstorage.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "accounts")
data class Account(
    @Id
    val id: String = "",
    val name: String = "",
    val age: Int = 0,
    val gender: Boolean = false,
    val createdTimestamp: Long = 0,
    val updatedTimestamp: Long = 0,
)

data class AccountEvent(
    val account: Account = Account(),
    val eventType: EventType = EventType.UNKNOWN,
    val timestamp: Long = 0,
)

enum class EventType {
    UNKNOWN,
    CREATE,
    UPDATE,
    DELETE,
}