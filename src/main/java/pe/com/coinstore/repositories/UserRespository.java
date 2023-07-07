package pe.com.coinstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.coinstore.entities.User;

public interface UserRespository extends JpaRepository<User, Long> {

    public User findByUserName(String userName);

}
