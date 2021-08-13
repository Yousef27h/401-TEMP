package com.example.foodapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "foods", path = "foods")
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByName(@Param("name") String name);
}
