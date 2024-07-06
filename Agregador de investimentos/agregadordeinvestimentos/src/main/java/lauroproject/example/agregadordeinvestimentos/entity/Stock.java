package lauroproject.example.agregadordeinvestimentos.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock {


    @Id
    @Column(name = "stockID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String stockId;

    @Column(name = "description")
    private String description;

    public Stock(String s, String description) {
    }
}
