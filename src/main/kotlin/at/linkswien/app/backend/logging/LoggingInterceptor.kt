package at.linkswien.app.backend.logging

import org.slf4j.LoggerFactory
import org.springframework.web.servlet.AsyncHandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoggingInterceptor : AsyncHandlerInterceptor {

    private val logger = LoggerFactory.getLogger(LoggingInterceptor::class.java)

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        exeption: Exception?
    ) {
        if (exeption == null) {
            logger.info(
                INFO_MESSAGE,
                request.method,
                request.requestURL,
                request.queryString.orEmpty(),
                response.status,
            )
        } else {
            logger.error(
                ERROR_MESSAGE,
                request.method,
                request.requestURL,
                request.queryString.orEmpty(),
                response.status,
                exeption.message,
                exeption)
        }
    }

    companion object {
        private const val INFO_MESSAGE = "Processing request method={} path={} params={} -> {}"
        private const val ERROR_MESSAGE = "$INFO_MESSAGE, caused by {}"
    }
}