package com.example.absservice.service;

import lombok.*;
import org.springframework.stereotype.*;

import com.example.absservice.exception.*;
import com.example.absservice.model.*;
import com.example.absservice.repository.*;

@Service
@RequiredArgsConstructor
public class ClientsService {

    private final ClientsRepository clientsRepository;

    public ClientEntity getClient(String passport) {
        return clientsRepository.findByPassport(passport)
            .orElseThrow(ClientNotFoundException::new);
    }

}
