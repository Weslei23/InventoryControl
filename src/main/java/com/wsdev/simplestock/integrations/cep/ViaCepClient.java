package com.wsdev.simplestock.integrations.cep;

import com.wsdev.simplestock.integrations.cep.dto.response.ViaCepResponseDTO;
import org.springframework.web.client.RestClient;

public class ViaCepClient
{
    private static final String BASE_URL = "https://viacep.com.br/ws/";

    public ViaCepResponseDTO searchCep( String cep ) throws Exception
    {
        String url = BASE_URL + cep + "/json/";

        RestClient restClient = RestClient.create();

        ViaCepResponseDTO viaCepResponseDTO = restClient.get().uri( url ).retrieve().body( ViaCepResponseDTO.class );

        return viaCepResponseDTO;
    }
}
