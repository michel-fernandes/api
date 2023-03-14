package br.com.j38.api.service;

import br.com.j38.api.domain.User;

public interface UserService {
    User findById(Integer id);
}
