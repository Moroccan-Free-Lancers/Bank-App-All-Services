package ma.master.compte.Service;

import ma.master.compte.Dto.AccountDto;
import ma.master.compte.Dto.DocumentsDto;
import ma.master.compte.Dto.OperationDto;

import java.util.List;

public interface AccountService {

    AccountDto getCompteById(String compteId);
    AccountDto postAccount(AccountDto accountDto);
    List<AccountDto> getAllCompteByUserId(String userId);
    List<OperationDto> getOperationsByCompteId(String compteId);
    List<DocumentsDto> getDocumentsByCompteId(String compteId);
    void putBalanceAccountById(String compteId, double balance);
}
