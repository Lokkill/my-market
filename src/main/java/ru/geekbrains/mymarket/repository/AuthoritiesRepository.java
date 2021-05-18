package ru.geekbrains.mymarket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mymarket.model.Authorities;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {
}
