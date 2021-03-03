package com.pruebaobsidian.backend.development;

import com.pruebaobsidian.backend.repository.NodeRepository;
import com.pruebaobsidian.backend.repository.domain.Node;
import com.pruebaobsidian.backend.repository.domain.NodeDesc;
import com.pruebaobsidian.backend.repository.domain.NodeRoot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NodeGenerator {

    NodeRepository nodeRepository;

    public NodeGenerator(final NodeRepository nodeRepository) {

        this.nodeRepository = nodeRepository;
        this.nodeRepository.deleteAll().subscribe();
        this.nodeRepository.insert(this.getNodeList()).subscribe();

    }

    private List<Node> getNodeList() {

        List<Node> nodeList = new ArrayList<>();

        nodeList.addAll(
                List.of(new NodeRoot("1", "nodeRoot"),
                        new NodeRoot("2", "nodeRoot"),
                        new NodeRoot("3", "nodeRoot"),
                        new NodeRoot("4", "nodeRoot"),
                        new NodeRoot("5", "nodeRoot"),
                        new NodeRoot("6", "nodeRoot"),
                        new NodeRoot("7", "nodeRoot")));

        nodeList.addAll(List.of(new NodeDesc("20", "nodeDesc", "2"),
                new NodeDesc("30", "nodeDesc", "3"),
                new NodeDesc("31", "nodeDesc", "3"),
                new NodeDesc("32", "nodeDesc", "3"),
                new NodeDesc("40", "nodeDesc", "4"),
                new NodeDesc("400", "nodeDesc", "40"),
                new NodeDesc("50", "nodeDesc", "5"),
                new NodeDesc("500", "nodeDesc", "50"),
                new NodeDesc("501", "nodeDesc", "50"),
                new NodeDesc("502", "nodeDesc", "50"),
                new NodeDesc("60", "nodeDesc", "6"),
                new NodeDesc("600", "nodeDesc", "60"),
                new NodeDesc("601", "nodeDesc", "60"),
                new NodeDesc("602", "nodeDesc", "60"),
                new NodeDesc("61", "nodeDesc", "6"),
                new NodeDesc("70", "nodeDesc", "7"),
                new NodeDesc("700", "nodeDesc", "70"),
                new NodeDesc("701", "nodeDesc", "70"),
                new NodeDesc("702", "nodeDesc", "70"),
                new NodeDesc("71", "nodeDesc", "7"),
                new NodeDesc("710", "nodeDesc", "71"),
                new NodeDesc("711", "nodeDesc", "71"),
                new NodeDesc("712", "nodeDesc", "71")));

        return nodeList;
    }

    
}
