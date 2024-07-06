package lauroproject.example.agregadordeinvestimentos.controller;

import lauroproject.example.agregadordeinvestimentos.service.AccountService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountController accountController) {
        this.accountService = accountService;
    }
}
