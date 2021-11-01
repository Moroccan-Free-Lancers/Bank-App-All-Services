package app.ws.bank_services_user.services;

import app.ws.bank_services_user.shared.dto.CounselorDto;


public interface CounselorService {

    CounselorDto createCounselor(CounselorDto counselorDto);

    CounselorDto updateCounselor(CounselorDto counselorDto, String counselorId);

}
