package com.pruebaobsidian.backend.repository;

import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.TreeNode;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface NodeRepositoryCustom {

    Flux<List<TreeNode<Node>>> findAllTrees();
}
