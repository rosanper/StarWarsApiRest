package com.letscode.starwarsapi.repositories;

import com.letscode.starwarsapi.models.Rebel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebelsRepository extends JpaRepository<Rebel,Long> {
}
