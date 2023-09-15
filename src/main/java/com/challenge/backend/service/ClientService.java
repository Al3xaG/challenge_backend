package com.challenge.backend.service;

import com.challenge.backend.domain.dto.ClientDTO;

public interface ClientService {
    ClientDTO createClient(ClientDTO clientDTO);
    ClientDTO getClient(String id);
    ClientDTO updateClient(ClientDTO clientDTO);
    Boolean deleteClient(String id);
}
