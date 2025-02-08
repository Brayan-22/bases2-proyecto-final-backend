package dev.alejandro.centralservice.service;

import dev.alejandro.centralservice.dto.DictarRequestDto;
import dev.alejandro.centralservice.dto.DictarResponseDto;
import dev.alejandro.centralservice.entity.DictarId;
import dev.alejandro.centralservice.exception.DictarNotCreatedException;
import dev.alejandro.centralservice.exception.DictarNotFoundException;

import java.util.List;

public interface IDictarService {
    DictarResponseDto save(DictarRequestDto requestDto) throws DictarNotCreatedException;

    List<DictarResponseDto> findAll() throws DictarNotFoundException;

    DictarResponseDto update(DictarId dictarId, DictarRequestDto requestDto) throws DictarNotCreatedException, DictarNotFoundException;

    void deleteByCodDictar(DictarId dictarId) throws DictarNotFoundException;
}
