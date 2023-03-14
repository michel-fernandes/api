package br.com.j38.api.service;

import br.com.j38.api.domain.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
}
