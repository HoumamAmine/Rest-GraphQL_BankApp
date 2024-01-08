package ma.houmam.service;

import ma.houmam.entities.BankAccount;
import ma.houmam.entities.Customer;
import ma.houmam.exceptions.BankAccountNotFoundException;
import ma.houmam.mappers.AccountMapper;
import ma.houmam.repositories.BankAccountRepository;
import ma.houmam.repositories.CustomerRepository;
import ma.houmam.dto.BankAccountRequestDTO;
import ma.houmam.dto.BankAccountResponseDTO;
import ma.houmam.enums.AccountType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    BankAccountRepository bankAccountRepository;
    CustomerRepository customerRepository;
    AccountMapper accountMapper;

    public AccountServiceImpl(BankAccountRepository bankAccountRepository, AccountMapper accountMapper, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountMapper = accountMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public BankAccountResponseDTO getAccountById(String id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException(String.format("Account %s not found", id)));
        return accountMapper.fromBankAccount(bankAccount);
    }

    @Override
    public BankAccountResponseDTO updateAccount(BankAccountRequestDTO bankAccountRequestDTO, String id) throws BankAccountNotFoundException {
        BankAccount bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException(String.format("Account %s not found", id)));
        BeanUtils.copyProperties(bankAccountRequestDTO, bankAccount);
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        return accountMapper.fromBankAccount(savedAccount);
    }

    @Override
    public List<BankAccountResponseDTO> getAllAccounts() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        return bankAccounts.stream().map(b -> accountMapper.fromBankAccount(b)).collect(Collectors.toList());
    }

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountRequestDTO) {
        BankAccount bankAccount = accountMapper.formBankAccountRequestDTO(bankAccountRequestDTO);
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        return accountMapper.fromBankAccount(savedAccount);
    }

    @Override
    public void deleteById(String id) throws BankAccountNotFoundException {
        if (bankAccountRepository.findById(id).orElse(null) == null)
            throw new BankAccountNotFoundException(String.format("Not such account %s found", id));
        bankAccountRepository.deleteById(id);
    }

    @Override
    public void populateData() {
        Stream.of("Scott", "Halpert", "Bing", "Galler").forEach(c->{
            Customer customer = Customer.builder()
                    .name(c)
                    .build();
            customerRepository.save(customer);
            for (int i = 0; i < 10; i++){
                BankAccount bankAccount= BankAccount.builder()
                        .id(i + 1 + "-->" + UUID.randomUUID())
                        .balance(10000 + Math.random() * 90000)
                        .createdAt(new Date())
                        .type(Math.random() < 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
                        .currency("USD")
                        .customer(customer)
                        .build();
                bankAccountRepository.save(bankAccount);
            }
        });
    }
}

