package ma.houmam.service;

import ma.houmam.exceptions.BankAccountNotFoundException;
import ma.houmam.dto.BankAccountRequestDTO;
import ma.houmam.dto.BankAccountResponseDTO;

import java.util.List;

public interface AccountService {
    BankAccountResponseDTO getAccountById(String id) throws BankAccountNotFoundException;
    BankAccountResponseDTO updateAccount(BankAccountRequestDTO bankAccountRequestDTO, String id) throws BankAccountNotFoundException;
    List<BankAccountResponseDTO> getAllAccounts();
    BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO);
    void deleteById(String id) throws BankAccountNotFoundException;
    void populateData();

}
