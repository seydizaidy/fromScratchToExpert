package com.zaidy.loadsaveimagespringangular.repo;

import com.zaidy.loadsaveimagespringangular.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long>
{
    Optional<Product> findByName(String name);
    Optional<Product> findById(Long id);
}
