package com.pruebaobsidian.backend;

import com.pruebaobsidian.backend.handler.NodeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class NodeHandlerConfig {

    @Bean
    public RouterFunction<ServerResponse> nodeGET(final NodeHandler nodeHandler) {

        return RouterFunctions.route(RequestPredicates.GET("/node"), nodeHandler::getNodeList)
                .andRoute(RequestPredicates.POST("/node"), nodeHandler::postNode)
                .andRoute(RequestPredicates.GET("/trees"), nodeHandler::getAllTrees);

    }
}
