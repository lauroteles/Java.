package lauroproject.example.agregadordeinvestimentos.entity;


import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "billingAddress")
public class BllingAddress {

    @Id
    @Column(name = "accountId")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "accountId")
    private Account account;

    @Column(name = "street")
    private String street;

    @Column(name="number")
    private Integer number;


    public BllingAddress() {
    }

    public BllingAddress(Integer number, String street, Account account, UUID id) {
        this.number = number;
        this.street = street;
        this.account = account;
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


}
