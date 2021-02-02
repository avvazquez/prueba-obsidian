package com.pruebaobsidian.backend.service;

import com.pruebaobsidian.backend.repository.NodeRepository;
import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.TreeNode;
import com.pruebaobsidian.backend.repository.mapper.NodeMapperImpl;
import com.pruebaobsidian.backend.service.domain.NodeDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;

    @Override
    public Flux<NodeDTO> findAll() {

        return nodeRepository.findAll().map(object -> object.mapper(new NodeMapperImpl()));
    }

    @Override
    public Mono<NodeDTO> save(final Node node) {

        return nodeRepository.save(node).map(object -> object.mapper(new NodeMapperImpl()));
    }

    @Override
    public Flux<List<TreeNode<NodeDTO>>> findAllTrees() {

        return nodeRepository.findAllTrees().map(object -> {

            List<TreeNode<NodeDTO>> toret = new ArrayList<>();

            object.forEach(treeNode -> {
                TreeNode<NodeDTO> nuevo = new TreeNode<>();
                nuevo.setSelf(treeNode.getSelf().mapper(new NodeMapperImpl()));
                nuevo.setChildren(new ArrayList<>());
                treeNode.getChildren()
                        .forEach(node -> nuevo.getChildren()
                                .add(node.mapper(new NodeMapperImpl())));
                toret.add(nuevo);
            });
            return toret;
        });
    }

    @Override
    public Mono<Void> deleteAll() {
        return nodeRepository.deleteAll();
    }

}
