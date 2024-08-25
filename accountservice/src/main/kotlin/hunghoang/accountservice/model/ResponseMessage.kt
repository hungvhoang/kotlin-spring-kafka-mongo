package hunghoang.accountservice.model

data class ResponseMessage(
    val success: Boolean = false,
    val message: String = "",
    val data: Any? = Any()
)