package lauroproject.example.agregadordeinvestimentos.service;


import lauroproject.example.agregadordeinvestimentos.controller.CreateStockDTO;
import lauroproject.example.agregadordeinvestimentos.entity.Stock;
import lauroproject.example.agregadordeinvestimentos.repository.StockRepositoryRepositoy;
import org.springframework.stereotype.Service;


@Service
public class StockService {

    private final StockRepositoryRepositoy stockRepositoryRepository;

    public StockService(StockRepositoryRepositoy stockRepositoryRepositoy) {
        this.stockRepositoryRepository = stockRepositoryRepositoy;
    }

    public void createStock(CreateStockDTO createStockDTO) {

        var stock = new Stock(
                createStockDTO.stockId(),
                createStockDTO.description()
        );
        stockRepositoryRepository.save(stock);
    }
}

