package at.linkswien.app.backend.logging

import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
@Order(10)
class LoggingWebFilter : WebFilter {

    private val logger = LoggerFactory.getLogger(LoggingWebFilter::class.java)

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request

        return chain.filter(exchange)
            .doOnEach { signal ->
                if (signal.isOnError) {
                    exchange.response.beforeCommit {
                        Mono.fromCallable {
                            logger.error(
                                ERROR_MESSAGE,
                                request.method,
                                request.path,
                                request.queryParams,
                                exchange.response.statusCode,
                                signal.throwable?.message,
                                signal.throwable
                            )
                            null
                        }
                    }
                }
                if (signal.isOnComplete) {
                    logger.info(
                        INFO_MESSAGE,
                        request.method,
                        request.path,
                        request.queryParams,
                        exchange.response.statusCode,
                    )
                }
            }
    }

    companion object {
        private const val INFO_MESSAGE = "Processing request method={} path={} params={} -> {}"
        private const val ERROR_MESSAGE = "$INFO_MESSAGE, caused by {}"
    }
}