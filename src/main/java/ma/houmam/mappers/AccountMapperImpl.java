package ma.houmam.mappers;

import ma.houmam.entities.BankAccount;
import ma.houmam.dto.BankAccountRequestDTO;
import ma.houmam.dto.BankAccountResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
@Service
public class AccountMapperImpl implements AccountMapper {
    @Override
    public BankAccount formBankAccountRequestDTO(BankAccountRequestDTO bankAccountRequestDTO){
        BankAccount bankAccount = new BankAccount();
        BeanUtils.copyProperties(bankAccountRequestDTO, bankAccount);
        bankAccount.setCreatedAt(new Date());
        bankAccount.setId(UUID.randomUUID().toString());
        return bankAccount;
    }
    @Override
    public BankAccountResponseDTO fromBankAccount(BankAccount bankAccount){
        BankAccountResponseDTO bankAccountResponseDTO = new BankAccountResponseDTO();
        BeanUtils.copyProperties(bankAccount, bankAccountResponseDTO);
        return bankAccountResponseDTO;
    }
}
