package com.pruebaobsidian.backend.development;

import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.NodeDesc;
import com.pruebaobsidian.backend.service.domain.NodeDTO;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PruebaWebClient {

    private WebClient client = WebClient.create("http://monbodb_container:8080");

    public Mono<Void> deleteNodes() {

        return client.delete().uri("/node/").retrieve().bodyToMono(Void.class);
    }

    public Flux<NodeDTO> getGetFlux() {

        return client.get().uri("/node/").accept(MediaType.TEXT_EVENT_STREAM).retrieve().bodyToFlux(NodeDTO.class);
    }

    public Mono<NodeDTO> getPostMono() {
        return client.post().uri("/node/").contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new NodeDesc(null, "POST", "1")))
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(NodeDTO.class);
    }

    public Flux<Node> getTrees() {
        return client.get().uri("/trees/").accept(MediaType.TEXT_EVENT_STREAM).retrieve()
                .bodyToFlux(Node.class);
    }


}
