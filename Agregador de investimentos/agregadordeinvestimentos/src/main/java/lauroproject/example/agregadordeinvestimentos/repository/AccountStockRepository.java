package lauroproject.example.agregadordeinvestimentos.repository;


import lauroproject.example.agregadordeinvestimentos.entity.AccountStock;
import lauroproject.example.agregadordeinvestimentos.entity.AccountStockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountStockRepository extends JpaRepository<AccountStock, AccountStockId> {
}
