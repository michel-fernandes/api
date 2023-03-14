package br.com.j38.api.service.impl;

import br.com.j38.api.domain.User;
import br.com.j38.api.repositories.UserRepository;
import br.com.j38.api.service.UserService;
import br.com.j38.api.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
