package com.pruebaobsidian.backend.repository.mapper;

import com.pruebaobsidian.backend.repository.domain.NodeDesc;
import com.pruebaobsidian.backend.repository.domain.NodeRoot;
import com.pruebaobsidian.backend.service.domain.NodeDTO;

public interface NodeMapper {
    NodeDTO visit(final NodeRoot node);

    NodeDTO visit(final NodeDesc node);

}
