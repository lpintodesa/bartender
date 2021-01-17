package com.luispintodesa.bartender.models.dao;

import com.luispintodesa.bartender.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

  User findByUsername(String username);

  User findById(int id);

}
