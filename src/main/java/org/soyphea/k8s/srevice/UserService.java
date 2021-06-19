package org.soyphea.k8s.srevice;

import org.soyphea.k8s.domain.Password;
import org.soyphea.k8s.domain.User;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    List<User> users = Arrays.asList(new User(1, "Dara"), new User(2, "Seyha"),new User(3, "Rodrigo"));

    public List<User> getUser(String containName) {
        Password password = new Password();
        String var = "hola mundo";
        password.setClave("123124124");
        if(password.getClave() == "123124124"){
            var = "Rodrigo";
            password.setClave("Rodrigo");
        }
        return users.stream().filter(user -> user.getName().contains(containName)).collect(Collectors.toList());
    }

    public List<User> getUser2(String containName) {
        Password password = new Password();
        String var = "hola mundo";
        password.setClave("123124124");
        if(password.getClave() == "123124124"){
            var = "Rodrigo";
            password.setClave("Rodrigo");
        }
        return users.stream().filter(user -> user.getName().contains(containName)).collect(Collectors.toList());
    }

}
