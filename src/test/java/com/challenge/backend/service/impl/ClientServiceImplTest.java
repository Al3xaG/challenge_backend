package com.challenge.backend.service.impl;

import com.challenge.backend.domain.dto.AccountDTO;
import com.challenge.backend.domain.dto.ClientDTO;
import com.challenge.backend.domain.entities.Account;
import com.challenge.backend.domain.entities.Client;
import com.challenge.backend.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {
    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientServiceImpl clientService;

    ClientDTO clientDTO;
    Client client;

    String id = "1";

    @BeforeEach
    void setUp(){
        List<AccountDTO> accountDTOS = new ArrayList<>();
        List<Account> accounts = new ArrayList<>();
        clientDTO = new ClientDTO();
        clientDTO.setClientId(Long.valueOf(id));
        clientDTO.setName("Marianela Montalvo");
        clientDTO.setGender("Femenino");
        clientDTO.setAge(25);
        clientDTO.setDni("172281254");
        clientDTO.setAddress("Amazonas y pereira");
        clientDTO.setPhoneNumber("0987654569");
        clientDTO.setPassword("3423");
        clientDTO.setStatus(true);
        clientDTO.setAccounts(accountDTOS);
        client = new Client();
        client.setClientId(Long.valueOf(id));
        client.setName("Marianela Montalvo");
        client.setGender("Femenino");
        client.setAge(25);
        client.setDni("172281254");
        client.setAddress("Amazonas y pereira");
        client.setPhoneNumber("0987654569");
        client.setPassword("3423");
        client.setStatus(true);
        client.setAccounts(accounts);
    }


    @Test
    void createClient() {
        when(clientRepository.save(any())).thenReturn(client);
        ClientDTO result = clientService.createClient(clientDTO);
        assertNotNull(result);
        assertEquals(client.getName(), result.getName());
        assertEquals(client.getDni(), result.getDni());
        assertEquals(client.getAge(), result.getAge());
        assertEquals(client.getPassword(), result.getPassword());
        assertEquals(client.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(client.getStatus(), result.getStatus());
    }

    @Test
    void getClient() {
        when(clientRepository.findByClientId(client.getClientId())).thenReturn(client);
        ClientDTO result = clientService.getClient(id);
        assertNotNull(result);
        assertEquals(client.getName(), result.getName());
        assertEquals(client.getDni(), result.getDni());
        assertEquals(client.getAge(), result.getAge());
        assertEquals(client.getPassword(), result.getPassword());
        assertEquals(client.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(client.getStatus(), result.getStatus());
    }

    @Test
    void updateClient() {
    }

    @Test
    void deleteClient() {
    }
}
