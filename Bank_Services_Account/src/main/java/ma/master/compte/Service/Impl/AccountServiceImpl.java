package ma.master.compte.Service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ma.master.compte.Service.Utils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.master.compte.Dto.AccountDto;
import ma.master.compte.Dto.DocumentsDto;
import ma.master.compte.Dto.OperationDto;
import ma.master.compte.Entity.AccountEntity;
import ma.master.compte.Entity.DocumentsEntity;
import ma.master.compte.Entity.OperationEntity;
import ma.master.compte.Exceptions.AccountException;
import ma.master.compte.Repository.AccountRepository;
import ma.master.compte.Service.AccountService;



@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountRepository accountRepository;

	@Autowired
	Utils utils;
	
	@Override
	public AccountDto getCompteById(String compteId) {
		ModelMapper modelMapper = new ModelMapper();
		AccountEntity compte = accountRepository.findByCompteId(compteId).orElseThrow( () -> new AccountException("Account not found"));
        
		return modelMapper.map(compte, AccountDto.class);
	}

	@Override
	public AccountDto postAccount(AccountDto accountDto) {

			AccountEntity account = new AccountEntity();
			BeanUtils.copyProperties(accountDto, account);
		  	account.setCompteId(utils.generatedStringId(32));
			AccountEntity accountCreated = accountRepository.save(account);
			AccountDto accountDtoTransfer = new AccountDto();
			BeanUtils.copyProperties(accountCreated, accountDtoTransfer);

			return accountDtoTransfer;

	}


	@Override
	public List<AccountDto> getAllCompteByUserId(String userId) {
		ModelMapper modelMapper = new ModelMapper();
		List<AccountDto> comptesDto = new ArrayList<>();
		List<AccountEntity> comptes = new ArrayList<>((Collection<? extends AccountEntity>) accountRepository.findAllByUserId(userId));
		comptes.forEach(compte -> comptesDto.add(modelMapper.map(compte, AccountDto.class)));
		return comptesDto;
	}

	@Override
	public List<OperationDto> getOperationsByCompteId(String compteId) {
		
		ModelMapper modelMapper = new ModelMapper();
		List<OperationDto> operationsDto = new ArrayList<>();
		List<OperationEntity> operations = accountRepository.findByCompteId(compteId).get().getOperation();
		operations.forEach(operation -> operationsDto.add(modelMapper.map(operation, OperationDto.class)));
		return operationsDto;
	}

	@Override
	public List<DocumentsDto> getDocumentsByCompteId(String compteId) {
		ModelMapper modelMapper = new ModelMapper();
		List<DocumentsDto> documentsDto = new ArrayList<>();
		List<DocumentsEntity> documents = accountRepository.findByCompteId(compteId).get().getDocument();
		documents.forEach(document -> documentsDto.add(modelMapper.map(document, DocumentsDto.class)));
		return documentsDto;
		
	}

	@Override
	public void putBalanceAccountById(String compteId, double balance) {

		AccountEntity account = accountRepository.findByCompteId(compteId).orElseThrow( () -> new AccountException("Account not found"));
		account.setBalance(balance);
		accountRepository.save(account);

		return ;
	}


}
