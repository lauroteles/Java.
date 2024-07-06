package lauroproject.example.agregadordeinvestimentos.repository;

import lauroproject.example.agregadordeinvestimentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepositoryRepository extends JpaRepository<User, UUID> {
}
