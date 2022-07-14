package com.example.demo.repository;

import com.example.demo.pojo.CustomerRelation;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CustomerRelationRepository extends Neo4jRepository<CustomerRelation,Long> {
}
