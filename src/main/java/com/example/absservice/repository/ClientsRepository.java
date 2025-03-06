package com.example.absservice.repository;

import java.util.*;

import org.springframework.data.jpa.repository.*;

import com.example.absservice.model.*;

public interface ClientsRepository extends JpaRepository<ClientEntity, Long> {

    @EntityGraph(value = "client_entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    Optional<ClientEntity> findByPassport(String passport);

}
