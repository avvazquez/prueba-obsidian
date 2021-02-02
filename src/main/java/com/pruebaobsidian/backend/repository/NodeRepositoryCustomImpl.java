package com.pruebaobsidian.backend.repository;

import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.TreeNode;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

public class NodeRepositoryCustomImpl implements NodeRepositoryCustom {

    private final ReactiveMongoOperations mongoOperations;

    public NodeRepositoryCustomImpl(final ReactiveMongoOperations mongoOperations) {

        Assert.notNull(mongoOperations, "MongoOperations must not be null!");
        this.mongoOperations = mongoOperations;
    }

    public Flux<List<TreeNode<Node>>> findAllTrees() {

        Query queryForRootNodes = new Query(Criteria.where("_class").is("root"));

        return mongoOperations
                .find(queryForRootNodes, Node.class)
                .map(node -> {

                    TreeNode<Node> treeNode = new TreeNode<>(node, new ArrayList<>());
                    return Flux.just(treeNode);

                }).map(object -> object.expand(parent -> {

                            Query queryForChildrenOfId = new Query(Criteria.where("parentId")
                                    .is(parent.getSelf().getId()));

                            return mongoOperations
                                    .find(queryForChildrenOfId, Node.class)
                                    .map(node -> {

                                        parent.getChildren().add(node);
                                        return new TreeNode<>(node, new ArrayList<>());

                                    });
                        }).buffer()
                ).flatMap(Flux::from)
                .log();
    }
}
