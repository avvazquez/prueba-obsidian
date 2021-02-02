package com.pruebaobsidian.backend.service;

import com.pruebaobsidian.backend.repository.NodeRepository;
import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.NodeDesc;
import com.pruebaobsidian.backend.repository.domain.NodeRoot;
import com.pruebaobsidian.backend.service.domain.NodeDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class NodeServiceImplTest {

    @MockBean
    NodeRepository nodeRepository;

    NodeServiceImpl nodeServiceImpl;

    @BeforeEach
    private void init() {
        nodeServiceImpl = new NodeServiceImpl(nodeRepository);
    }

    @Test
    void shouldReturnList() {

        Node node1 = new NodeDesc("33", "primero", "23");
        Node node2 = new NodeRoot("23", "segundo");
        when(this.nodeRepository.findAll())
                .thenReturn(Flux.just(node1, node2));


        Flux<NodeDTO> publisher = this.nodeServiceImpl.findAll();

        Predicate<NodeDTO> predicate1 = node -> null != node.getId() && node.getDescripcion().equalsIgnoreCase("primero") && node.getParentId().equalsIgnoreCase("23");

        Predicate<NodeDTO> predicate2 = node -> null != node.getId() && node.getDescripcion().equalsIgnoreCase("segundo");

        StepVerifier.create(publisher)
                .expectNextMatches(predicate1)
                .expectNextMatches(predicate2)
                .verifyComplete();
    }

    @Test
    void shouldReturnAfterTheSave() {

        Node node1 = new NodeDesc("33", "primero", "23");
        when(this.nodeRepository.save(node1)).thenReturn(Mono.just(node1));

        Mono<NodeDTO> publisher = this.nodeServiceImpl.save(node1);

        Predicate<NodeDTO> predicate1 = node -> null != node.getId() && node.getDescripcion().equalsIgnoreCase("primero") && node.getParentId().equalsIgnoreCase("23");

        StepVerifier
                .create(publisher)
                .expectNextMatches(predicate1)
                .verifyComplete();
    }
}
