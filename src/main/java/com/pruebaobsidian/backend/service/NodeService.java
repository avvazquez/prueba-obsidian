package com.pruebaobsidian.backend.service;

import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.TreeNode;
import com.pruebaobsidian.backend.service.domain.NodeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NodeService {
    Flux<NodeDTO> findAll();

    Mono<NodeDTO> save(final Node node);

    Mono<Void> deleteAll();

    Flux<List<TreeNode<NodeDTO>>> findAllTrees();
}
