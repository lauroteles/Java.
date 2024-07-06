package lauroproject.example.agregadordeinvestimentos.service;

import lauroproject.example.agregadordeinvestimentos.controller.AccountResponseDto;
import lauroproject.example.agregadordeinvestimentos.controller.CreateAccountDTO;
import lauroproject.example.agregadordeinvestimentos.controller.CreateUserDto;
import lauroproject.example.agregadordeinvestimentos.controller.UpdateUserDTO;
import lauroproject.example.agregadordeinvestimentos.entity.Account;
import lauroproject.example.agregadordeinvestimentos.entity.BllingAddress;
import lauroproject.example.agregadordeinvestimentos.entity.User;
import lauroproject.example.agregadordeinvestimentos.repository.AccountRepository;
import lauroproject.example.agregadordeinvestimentos.repository.BillAddressRepo;
import lauroproject.example.agregadordeinvestimentos.repository.UserRepositoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    private UserRepositoryRepository userRepository;

    private AccountRepository accountRepository;

    private BillAddressRepo billAddressRepo;

    public UserService(UserRepositoryRepository userRepository, AccountRepository accountRepository,BillAddressRepo billingAddress) {
        this.userRepository = userRepository;
        this.billAddressRepo = billingAddress;
        this.accountRepository = accountRepository;
    }

    public  UUID createUser(CreateUserDto createUserDto) {

        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);


        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();

    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }


    public void updateUserById(String userId,
                               UpdateUserDTO updateUserDTO) {
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if (userEntity.isPresent()) {
            var user = userEntity.get();

            if (updateUserDTO.username() != null) {
                user.setUsername(updateUserDTO.username());
            }

            if (updateUserDTO.password() != null) {
                user.setPassword((updateUserDTO.password()));
            }

            userRepository.save(user);
            //
        }

    }

    public void deleteById(String userId) {

        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(id);

        if (userExists) {
            userRepository.deleteById(id);
        }
    }


    public void createAccount(String userId, CreateAccountDTO createAccountDTO) {

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var account = new Account(
                UUID.randomUUID(),
                user,
                null,
                createAccountDTO.description(),
                new ArrayList<>()
                        );

        var accountCreated = accountRepository.save(account);

        var billingAddress = new BllingAddress(
                    createAccountDTO.number(),
                    createAccountDTO.street(),
                    account,
                    accountCreated.getAccountId()
        );

        billAddressRepo.save(billingAddress);


    }

    public List<AccountResponseDto> listAccounts(String userId){

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var accounts = user.getAccounts()
                .stream()
                .map(ac -> new AccountResponseDto(ac.getAccountId(),ac.getDescripption()))
                .toList();

        return accounts;
    }
}

