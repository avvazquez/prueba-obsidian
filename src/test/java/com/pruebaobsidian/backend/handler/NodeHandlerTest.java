package com.pruebaobsidian.backend.handler;

import com.pruebaobsidian.backend.NodeHandlerConfig;
import com.pruebaobsidian.backend.development.NodeGenerator;
import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.NodeDesc;
import com.pruebaobsidian.backend.service.NodeService;
import com.pruebaobsidian.backend.service.domain.NodeDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.BodyContentSpec;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import(NodeHandlerConfig.class)
class NodeHandlerTest {

    @MockBean
    public NodeGenerator generadorNodos;
    @MockBean
    public NodeService nodeServiceImpl;
    @MockBean
    public ReactiveMongoTemplate reactiveMongoTemplate;
    @Autowired
    private WebTestClient client;

    @Test
    void shouldGetList() {

        NodeDTO nodeDto1 = new NodeDTO("33", "primero", "el de delante");
        NodeDTO nodeDto2 = new NodeDTO("aa", "segundo", null);

        when(this.nodeServiceImpl.findAll())
                .thenReturn(Flux.just(nodeDto1, nodeDto2));

        when(this.nodeServiceImpl.deleteAll())
                .thenReturn(Mono.empty());

        BodyContentSpec bodyContent = this.client.get()
                .uri("http://localhost:8080/node")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("text/event-stream;charset=UTF-8")
                .expectBody()
                .consumeWith(System.out::println);

        String bodyString = new String(Objects.requireNonNull(bodyContent.returnResult().getResponseBody()));
        Assertions.assertThat(bodyString.contains("{\"id\":\"33\",\"descripcion\":\"primero\",\"parentId\":\"el de delante\"}")).isTrue();
        Assertions.assertThat(bodyString.contains("{\"id\":\"aa\",\"descripcion\":\"segundo\",\"parentId\":null}")).isTrue();

    }

    @Test
    void shouldGivenNodeByPost() {

        Node node1 = new NodeDesc("33", "primero", "asdf");

        when(this.nodeServiceImpl.save(Mockito.any(Node.class)))
                .thenReturn(Mono.just(new NodeDTO("33", "primero", "asdf")));

        BodyContentSpec bodyContent = client.post()
                .uri("http://localhost:8080/node")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(node1))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("@.id").exists()
                .jsonPath("@.id").isNotEmpty()
                .jsonPath("@.descripcion").isEqualTo("primero")
                .jsonPath("@.parentId").isEqualTo("asdf");


        String bodyString = new String(Objects.requireNonNull(bodyContent.returnResult().getResponseBody()));
        Assertions.assertThat(bodyString.contains("{\"id\":\"33\",\"descripcion\":\"primero\",\"parentId\":\"asdf\"}")).isTrue();

    }
}
