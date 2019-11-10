package br.com.springboot.demo.mapper;

import br.com.springboot.demo.domain.UserRequest;
import br.com.springboot.demo.domain.UserResponse;
import br.com.springboot.demo.domain.UsersResponse;
import br.com.springboot.demo.model.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public static final UsersResponse mapper(List<User> user){

        return UsersResponse.builder().users(
                user.stream().map(UsersResponse.User::new).collect(Collectors.toList())
        ).build();
    }

    public static final User mapper(UserRequest request){
        return new User(request.getName(), request.getAge());
    }

    public static final UserResponse mapper(User user){
        return UserResponse.builder()
                .user(UserResponse.User.builder()
                        .id(user.getId())
                        .age(user.getAge())
                        .name(user.getName())
                        .build())
                .build();

    }

}
