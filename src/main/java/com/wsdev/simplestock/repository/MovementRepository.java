package com.wsdev.simplestock.repository;

import com.wsdev.simplestock.entity.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long>
{
}
