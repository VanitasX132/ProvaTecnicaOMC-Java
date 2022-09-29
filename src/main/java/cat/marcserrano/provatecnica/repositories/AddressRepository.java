package cat.marcserrano.provatecnica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.marcserrano.provatecnica.entities.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {

}
