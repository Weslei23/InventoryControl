package com.wsdev.simplestock.repository;

import com.wsdev.simplestock.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long>
{
    Supplier findByName( String name );
}
