package com.challenge.backend.service.impl;

import com.challenge.backend.domain.dto.ClientDTO;
import com.challenge.backend.domain.entities.Client;
import com.challenge.backend.exceptions.NotFoundException;
import com.challenge.backend.repository.ClientRepository;
import com.challenge.backend.service.ClientService;
import com.challenge.backend.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    @Override
    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = clientRepository.save(convertDTOtoEntity(clientDTO));
        return convertEntityToDTO(client);
    }

    @Override
    public ClientDTO getClient(String id) {
        return convertEntityToDTO(clientRepository.findByClientId(Long.valueOf(id)));
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        Optional<Client> optional = clientRepository.findById(Long.valueOf(clientDTO.getClientId()));
        if (!optional.isPresent()){
            throw  new NotFoundException("Cliente no existe");
        }
        Client client = clientRepository.save(convertDTOtoEntity(clientDTO));
        return convertEntityToDTO(client);
    }

    @Override
    public Boolean deleteClient(String id) {
        Optional<Client> optional = clientRepository.findById(Long.valueOf(id));
        if (!optional.isPresent()){
            throw  new NotFoundException("Cliente no existe");
        }
        clientRepository.deleteById(Long.valueOf(id));
        return true;
    }
    private Client convertDTOtoEntity(ClientDTO clientDTO){
        return Mapper.modelMapper().map(clientDTO, Client.class);
    }

    private ClientDTO convertEntityToDTO(Client client){
        return Mapper.modelMapper().map(client, ClientDTO.class);
    }
}
