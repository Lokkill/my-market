package ru.geekbrains.mymarket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mymarket.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
