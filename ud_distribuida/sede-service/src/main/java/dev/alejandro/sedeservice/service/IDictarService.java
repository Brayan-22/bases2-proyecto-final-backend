package dev.alejandro.sedeservice.service;

import dev.alejandro.sedeservice.dto.DictarRequestDto;
import dev.alejandro.sedeservice.dto.DictarResponseDto;
import dev.alejandro.sedeservice.entity.DictarId;
import dev.alejandro.sedeservice.exception.DictarNotCreatedException;
import dev.alejandro.sedeservice.exception.DictarNotFoundException;

import java.util.List;

public interface IDictarService {
    DictarResponseDto save(DictarRequestDto requestDto) throws DictarNotCreatedException;

    List<DictarResponseDto> findAll() throws DictarNotFoundException;

    DictarResponseDto update(DictarId dictarId, DictarRequestDto requestDto) throws DictarNotCreatedException, DictarNotFoundException;

    void deleteByCodDictar(DictarId dictarId) throws DictarNotFoundException;
}
