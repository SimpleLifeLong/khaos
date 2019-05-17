package org.greece.mythology.gaea.filters;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationGatewayFilterFactory extends AbstractGatewayFilterFactory {
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            String headerToken = exchange.getRequest().getHeaders().getFirst("Authorization");
            if (StringUtils.equals(headerToken, "UUID")) {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        };
    }
}
