package lauroproject.example.agregadordeinvestimentos.repository;


import lauroproject.example.agregadordeinvestimentos.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepositoryRepositoy extends JpaRepository<Stock, String> {
}
