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
public class DictionariesApiController implements DictionariesApi {

    private final DictionariesService dictionariesService;

    private final ModelMapper modelMapper;

    @PreAuthorize("hasRole('operator')")
    @Override
    public ResponseEntity<ParametersDto> dictionariesGet() {
        var dictionaries = dictionariesService.getDictionaries();
        var responseDto = modelMapper.map(dictionaries, ParametersDto.class);
        return ResponseEntity.ok(responseDto);
    }

}
