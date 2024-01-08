package ma.houmam.mappers;

import ma.houmam.dto.BankAccountRequestDTO;
import ma.houmam.dto.BankAccountResponseDTO;
import ma.houmam.entities.BankAccount;

public interface AccountMapper {
    BankAccount formBankAccountRequestDTO(BankAccountRequestDTO bankAccountRequestDTO);
    BankAccountResponseDTO fromBankAccount(BankAccount bankAccount);
}
