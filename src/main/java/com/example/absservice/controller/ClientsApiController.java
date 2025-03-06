package com.example.absservice.controller;

import lombok.*;
import org.modelmapper.*;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

import com.example.absservice.service.*;
import com.example.absservice.swagger.api.*;
import com.example.absservice.swagger.dto.*;

@RestController
@RequestMapping("api/v2/abs/")
@RequiredArgsConstructor
public class ClientsApiController implements ClientsApi {

    private final ClientsService clientsService;

    private final ModelMapper mapper;

    @PreAuthorize("hasRole('operator')")
    @Override
    public ResponseEntity<ClientDto> getClient(String passport) {
        var clientEntity = clientsService.getClient(passport);
        var clientDto = mapper.map(clientEntity, ClientDto.class);
        return ResponseEntity.ok(clientDto);
    }

}
