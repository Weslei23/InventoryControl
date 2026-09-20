package com.wsdev.simplestock.domain.movement.model.enums;

public enum MovementType
{
    INBOUND( "Entrada" ),  // Entrada / Recebimento
    OUTBOUND( "Saída" ), // Saída / Despacho
    ADJUSTMENT( "Ajuste" );// Ajus

    private String description;

    MovementType( String description )
    {
        this.description = description;
    }

    public String getDescription()
    {
        return this.description;
    }
}
