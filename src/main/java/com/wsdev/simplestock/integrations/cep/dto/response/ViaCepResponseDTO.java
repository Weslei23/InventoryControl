package com.wsdev.simplestock.integrations.cep.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ViaCepResponseDTO
{
    @JsonProperty( "cep" )
    private String zipCode;

    @JsonProperty( "logradouro" )
    private String street;

    @JsonProperty( "complemento" )
    private String complement;

    @JsonProperty( "bairro" )
    private String neighborhood;

    @JsonProperty( "localidade" )
    private String city;

    @JsonProperty( "uf" )
    private String state;

    @JsonProperty( "regiao" )
    private String region;
}
