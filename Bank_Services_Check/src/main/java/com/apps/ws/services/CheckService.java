package com.apps.ws.services;

import com.apps.ws.shared.dto.CheckDto;

import java.util.List;

public interface CheckService {

    CheckDto postCheck(CheckDto checkDto);
    String removeCheck(String id);
    CheckDto getCheck(String id);
    List<CheckDto> getAllCheckByUser(String userid);
}
