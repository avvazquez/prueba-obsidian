package com.pruebaobsidian.backend.repository.domain;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class NodeDeserializer extends StdDeserializer<Node> {

    private static final long serialVersionUID = 1L;

    public NodeDeserializer() {
        this(null);
    }

    public NodeDeserializer(final Class<?> vc) {
        super(vc);
    }

    @Override
    public Node deserialize(final JsonParser jp, final DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        String id = node.hasNonNull("id")
                && StringUtils.hasText(node.get("id").asText()) ? node.get("id").asText() : null;
        String descripcion = node.hasNonNull("descripcion")
                && StringUtils.hasText(node.get("descripcion").asText()) ? node.get("descripcion").asText() : null;
        String parentId = node.hasNonNull("parentId")
                && StringUtils.hasText(node.get("parentId").asText()) ? node.get("parentId").asText() : null;

        return node.has("parentId") ? new NodeDesc(id, descripcion, parentId) : new NodeRoot(id, descripcion);
    }
}