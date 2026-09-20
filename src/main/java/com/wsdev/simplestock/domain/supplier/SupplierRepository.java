package com.wsdev.simplestock.domain.supplier;

import com.wsdev.simplestock.domain.supplier.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long>
{
    Supplier findByName( String name );
}
