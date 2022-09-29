package cat.marcserrano.provatecnica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.marcserrano.provatecnica.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
