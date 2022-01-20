package am.ik.sorry;

import java.util.Locale;

import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

public class SorryHandler {

	public RouterFunction<ServerResponse> routes() {
		return route().route(x -> true, this::sorry).build();
	}

	Mono<ServerResponse> sorry(ServerRequest req) {
		final Locale locale = req.exchange().getLocaleContext().getLocale();
		final String message;
		if (locale != null && "ja".equalsIgnoreCase(locale.getLanguage())) {
			message = "現在メンテナンス中です。ご迷惑をおかけします。";
		}
		else {
			message = "Server under maintenance, sorry for the inconveniences.";
		}
		return ServerResponse.status(SERVICE_UNAVAILABLE).bodyValue(message);
	}
}
