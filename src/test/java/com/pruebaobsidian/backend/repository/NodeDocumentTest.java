package com.pruebaobsidian.backend.repository;

import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.NodeDesc;
import com.pruebaobsidian.backend.repository.domain.NodeRoot;
import com.pruebaobsidian.backend.repository.domain.TreeNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@DataMongoTest
@AutoConfigureDataMongo
class NodeDocumentTest {


    @Autowired
    NodeRepository repository;

    @Test
    void shouldReturnNode() throws Exception {

        Node node1 = new NodeDesc("33", "primero", "23");
        Node node2 = new NodeRoot("23", "segundo");

        Mono<Node> result1 = this.repository.save(node1);
        Mono<Node> result2 = this.repository.save(node2);

        Predicate<Node> predicate1 = node -> null != node.getId() && node.getDescripcion()
                .equalsIgnoreCase("primero") && ((NodeDesc) node).getParentId().equalsIgnoreCase("23");

        Predicate<Node> predicate2 = node -> null != node.getId()
                && node instanceof NodeRoot
                && node.getDescripcion().equalsIgnoreCase("segundo");

        StepVerifier
                .create(result1)
                .expectNextMatches(predicate1)
                .verifyComplete();

        StepVerifier
                .create(result2)
                .expectNextMatches(predicate2)
                .verifyComplete();
    }

    @Test
    void shouldReturnNodeList() throws Exception {

        Node node1 = new NodeDesc("33", "primero", "23");
        Node node2 = new NodeRoot("23", "segundo");

        Flux<Node> data = Flux.just(node1, node2);
        Flux<Node> result = this.repository.findAll();

        Predicate<Node> predicate1 = node -> null != node.getId() && node.getDescripcion()
                .equalsIgnoreCase("primero") && ((NodeDesc) node).getParentId().equalsIgnoreCase("23") || node instanceof NodeRoot && node.getDescripcion().equalsIgnoreCase("segundo");

        StepVerifier
                .create(repository.deleteAll())
                .verifyComplete();

        StepVerifier
                .create(data.flatMap(repository::save))
                .expectNextCount(2)
                .verifyComplete();

        StepVerifier
                .create(result)
                .expectNextMatches(predicate1)
                .expectNextMatches(predicate1)
                .verifyComplete();

    }

    @Test
    void shouldReturnNodeTree() throws Exception {

        Node root1 = new NodeRoot("1", "Root 1");
        Node root2 = new NodeRoot("2", "Root 2");
        Node desc1_1 = new NodeDesc("3", "Desc 1_1", "1");
        Node desc1_2 = new NodeDesc("4", "Desc 1_2", "1");
        Node desc1_1_1 = new NodeDesc("5", "Desc 1_1_1", "3");
        Node desc1_1_2 = new NodeDesc("6", "Desc 1_1_2", "3");
        Node desc2_1 = new NodeDesc("7", "Desc 2_1", "2");

        TreeNode<Node> treeNode1 = new TreeNode<Node>(root1, Arrays.asList(desc1_1, desc1_2));
        TreeNode<Node> treeNode2 = new TreeNode<Node>(desc1_1, Arrays.asList(desc1_1_1, desc1_1_2));
        TreeNode<Node> treeNode3 = new TreeNode<Node>(desc1_2, Arrays.asList());
        TreeNode<Node> treeNode4 = new TreeNode<Node>(desc1_1_1, Arrays.asList());
        TreeNode<Node> treeNode5 = new TreeNode<Node>(desc1_1_2, Arrays.asList());
        List<TreeNode<Node>> trees1 = Arrays.asList(treeNode1, treeNode2, treeNode3, treeNode4, treeNode5);

        TreeNode<Node> treeNode6 = new TreeNode<Node>(root2, Arrays.asList(desc2_1));
        TreeNode<Node> treeNode7 = new TreeNode<Node>(desc2_1, Arrays.asList());
        List<TreeNode<Node>> trees2 = Arrays.asList(treeNode6, treeNode7);

        Flux<Node> data = Flux.just(root1, root2, desc1_1, desc1_2, desc1_1_1, desc1_1_2, desc2_1);
        Flux<List<TreeNode<Node>>> result = this.repository.findAllTrees();


        Collections.sort(trees1, (n1, n2) -> n1.getSelf().getId().compareTo(n2.getSelf().getId()));
        for (TreeNode<Node> node : trees1) {
            Collections.sort(node.getChildren(), (n1, n2) -> n1.getId().compareTo(n2.getId()));
        }

        Collections.sort(trees2, (n1, n2) -> n1.getSelf().getId().compareTo(n2.getSelf().getId()));
        for (TreeNode<Node> node : trees2) {
            Collections.sort(node.getChildren(), (n1, n2) -> n1.getId().compareTo(n2.getId()));
        }

        Predicate<List<TreeNode<Node>>> predicate1 = treeNodeList -> {

            Collections.sort(treeNodeList, (tn1, tn2) -> tn1.getSelf().getId().compareTo(tn2.getSelf().getId()));
            for (TreeNode<Node> node : treeNodeList) {
                Collections.sort(node.getChildren(), (n1, n2) -> n1.getId().compareTo(n2.getId()));
            }

            return treeNodeList.toString().equals(trees1.toString()) || treeNodeList.toString().equals(trees2.toString());
        };

        StepVerifier
                .create(repository.deleteAll())
                .verifyComplete();

        StepVerifier
                .create(data.flatMap(repository::save))
                .expectNextCount(7)
                .verifyComplete();

        StepVerifier
                .create(repository.findAll())
                .expectNextCount(7)
                .verifyComplete();

        StepVerifier
                .create(result)
                .expectNextMatches(predicate1)
                .expectNextMatches(predicate1)
                .verifyComplete();

    }

    @TestConfiguration
    static class TestConf {


    }
}
