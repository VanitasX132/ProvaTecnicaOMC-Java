package cat.marcserrano.provatecnica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import cat.marcserrano.provatecnica.entities.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {

}
