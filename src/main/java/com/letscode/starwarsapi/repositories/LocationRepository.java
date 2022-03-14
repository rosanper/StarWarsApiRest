package com.letscode.starwarsapi.repositories;

import com.letscode.starwarsapi.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Long> {
}
