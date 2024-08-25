package hunghoang.accountstorage.model

data class AccountEntity(
    val account: Account = Account(),
    val createdTimestamp: Long = 0,
    val updatedTimeStamp: Long = 0,
)

data class Account(
    val id: String = "",
    val name: String = "",
    val age: Int = 0,
    val gender: Boolean = false,
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