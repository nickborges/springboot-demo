package br.com.springboot.demo.service;

import br.com.springboot.demo.domain.UserRequest;
import br.com.springboot.demo.domain.UserResponse;
import br.com.springboot.demo.domain.UsersResponse;
import br.com.springboot.demo.mapper.UserMapper;
import br.com.springboot.demo.model.User;
import br.com.springboot.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.springboot.demo.config.validation.NotFoundException;

@Service
public class SpringbootDemoService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UsersResponse execute(Pageable page){
        return userMapper.mapper(
                userRepository.findAll(page).getContent()
        );
    }

    public UserResponse execute(final Long id){
        return userMapper.mapper(
                findById(id)
        );
    }

    public UserResponse execute(final UserRequest request){
        return userMapper.mapper(
                userRepository.save(userMapper.mapper(request))
        );

    }

    public UserResponse execute(final Long id, final UserRequest request){
        User user = findById(id);
        user.setName(request.getName());
        user.setAge(request.getAge());
        return userMapper.mapper(user);
    }

    public UserResponse executed(final Long id){
        User user = findById(id);

        userRepository.deleteById(user.getId());

        return userMapper.mapper(user);
    }

    private User findById(final Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }
}
