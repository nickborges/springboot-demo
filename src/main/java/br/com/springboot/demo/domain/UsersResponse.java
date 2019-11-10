package br.com.springboot.demo.domain;

import lombok.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UsersResponse implements Serializable {

    public List<User> users;

    @Getter
    public static class User{
        private Long id;
        private String name;
        private int age;

        public User(br.com.springboot.demo.model.User user) {
            this.id = user.getId();
            this.name = user.getName();
            this.age = user.getAge();
        }
    }

}
