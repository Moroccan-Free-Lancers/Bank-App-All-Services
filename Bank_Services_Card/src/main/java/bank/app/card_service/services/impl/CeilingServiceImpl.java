package bank.app.card_service.services.impl;

import bank.app.card_service.entities.Ceiling;
import bank.app.card_service.exceptions.CeilingException;
import bank.app.card_service.repositories.CeilingRepository;
import bank.app.card_service.services.CeilingService;
import bank.app.card_service.shared.Utils;
import bank.app.card_service.shared.dto.CeilingDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CeilingServiceImpl implements CeilingService {

    @Autowired
    CeilingRepository ceilingRepository;

    @Autowired
    Utils utils;

    @Override
    public CeilingDto getCeiling(String ceilingId) {

        ModelMapper modelMapper = new ModelMapper();
        Ceiling ceiling = ceilingRepository.findByCeilingId(ceilingId).orElseThrow(
                () -> new CeilingException("Ceiling not found !"));

        return modelMapper.map(ceiling, CeilingDto.class);
    }

    @Override
    public CeilingDto postCeiling(CeilingDto ceilingDto){
        Ceiling ceiling = new Ceiling();

        BeanUtils.copyProperties(ceilingDto, ceiling);
        ceiling.setCeilingId(utils.generatedStringId(32));
        Ceiling ceilingCreated = ceilingRepository.save(ceiling);
        CeilingDto ceilingDtoTransfer = new CeilingDto();
        BeanUtils.copyProperties(ceilingCreated, ceilingDtoTransfer);

        return ceilingDtoTransfer;
    }

    @Override
    public List<CeilingDto> getCeilings() {

        ModelMapper modelMapper = new ModelMapper();
        List<CeilingDto> ceilingsDto = new ArrayList<>();
        List<Ceiling> ceilings = new ArrayList<>((Collection<? extends Ceiling>) ceilingRepository.findAll());
        ceilings.forEach(ceiling -> ceilingsDto.add(modelMapper.map(ceiling, CeilingDto.class)));

        return ceilingsDto;
    }

}

