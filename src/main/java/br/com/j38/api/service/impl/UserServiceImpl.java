package br.com.j38.api.service.impl;

import br.com.j38.api.domain.User;
import br.com.j38.api.domain.dto.UserDTO;
import br.com.j38.api.repositories.UserRepository;
import br.com.j38.api.service.UserService;
import br.com.j38.api.service.exceptions.DataIntegretyViolationException;
import br.com.j38.api.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }
    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findbyEmail(userDTO);
        return userRepository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public User update(UserDTO userDTO) {
        findById(userDTO.getId());
        findbyEmail(userDTO);
        return userRepository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findbyEmail(UserDTO userDTO){
        Optional<User> userOptional = userRepository.findByEmail(userDTO.getEmail());
        if(userOptional.isPresent() && !userOptional.get().getId().equals(userDTO.getId())){
            throw new DataIntegretyViolationException("E-mail já cadastrado no sistema");
        }
    }

}
