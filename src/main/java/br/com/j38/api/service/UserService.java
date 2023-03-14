package br.com.j38.api.service;

import br.com.j38.api.domain.User;
import br.com.j38.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO userDTO);
    User update(UserDTO userDTO);
}
