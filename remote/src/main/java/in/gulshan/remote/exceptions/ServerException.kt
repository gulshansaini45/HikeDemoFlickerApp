package `in`.gulshan.remote.exceptions

sealed class ServerException(msg: String) : RuntimeException(msg) {
    class DefaultErrorResponse(message: String) : ServerException(message)
    class UserUnAuthorizedResponse(message: String) : ServerException(message)
}
