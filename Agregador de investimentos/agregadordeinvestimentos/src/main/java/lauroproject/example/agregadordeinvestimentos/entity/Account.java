package lauroproject.example.agregadordeinvestimentos.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
public class Account {

    @Id
    @Column(name = "accountId")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BllingAddress billingAddress;

    @Column(name = "description")
    private String descripption;

    public  Account () {

    }

    public <E> Account(UUID uuid, User user, Object o, String description, ArrayList<E> es) {
    }

    public String getDescripption() {
        return descripption;
    }

    public void setDescripption(String descripption) {
        this.descripption = descripption;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public Account(UUID accountId, String descripption) {
        this.accountId = accountId;
        this.descripption = descripption;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
