package com.pruebaobsidian.backend.handler;

import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.TreeNode;
import com.pruebaobsidian.backend.service.NodeService;
import com.pruebaobsidian.backend.service.domain.NodeDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@AllArgsConstructor
@Controller
public class NodeHandler {

    private final NodeService nodeService;


    public Mono<ServerResponse> getNodeList(final ServerRequest request) {

        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(BodyInserters.fromPublisher(nodeService.findAll(), NodeDTO.class));
    }

    public Mono<ServerResponse> postNode(final ServerRequest request) {

        return ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(
                        request.bodyToMono(Node.class).flatMap(nodeService::save), NodeDTO.class));
    }

    public Mono<ServerResponse> getAllTrees(final ServerRequest request) {
        ParameterizedTypeReference<List<TreeNode<NodeDTO>>> typeRef = new ParameterizedTypeReference<>() {
        };
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM)
                .body(BodyInserters.fromPublisher(nodeService.findAllTrees(), typeRef));
    }

}
