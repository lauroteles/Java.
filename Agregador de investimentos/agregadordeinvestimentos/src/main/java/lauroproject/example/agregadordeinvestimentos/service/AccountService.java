package lauroproject.example.agregadordeinvestimentos.service;

import lauroproject.example.agregadordeinvestimentos.repository.AccountRepository;
import lauroproject.example.agregadordeinvestimentos.repository.StockRepositoryRepositoy;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository, StockRepositoryRepositoy stockRepositoryRepositoy) {
        this.accountRepository = accountRepository;
        this.stockRepositoryRepositoy = stockRepositoryRepositoy;
    }

    private StockRepositoryRepositoy stockRepositoryRepositoy;

}
