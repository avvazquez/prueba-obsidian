package com.pruebaobsidian.backend.repository;

import com.pruebaobsidian.backend.repository.domain.Node;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NodeRepository extends ReactiveMongoRepository<Node, String>, NodeRepositoryCustom {


}
