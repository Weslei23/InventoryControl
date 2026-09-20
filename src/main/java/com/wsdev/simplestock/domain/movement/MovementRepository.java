package com.wsdev.simplestock.domain.movement;

import com.wsdev.simplestock.domain.movement.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovementRepository extends JpaRepository<Movement, Long>
{
}
