package com.pruebaobsidian.backend.repository.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class NodeTest {

    @Test
    void shouldReturnNodeDesc() {

        NodeDesc node = new NodeDesc(null, "desc", "desc");

        Assertions.assertThat(node.getId()).isNull();
        Assertions.assertThat(node.getDescripcion()).isEqualTo("desc");
        Assertions.assertThat(node.getParentId()).isEqualTo("desc");

    }

    @Test
    void shouldReturnNodeRoot() {

        NodeRoot node = new NodeRoot(null, "root");

        Assertions.assertThat(node.getId()).isNull();
        Assertions.assertThat(node.getDescripcion()).isEqualTo("root");

    }
}
