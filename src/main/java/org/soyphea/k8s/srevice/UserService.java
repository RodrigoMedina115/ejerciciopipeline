package org.soyphea.k8s.srevice;

import org.soyphea.k8s.domain.Password;
import org.soyphea.k8s.domain.User;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    List<User> users = Arrays.asList(new User(1, "Dara"), new User(2, "Seyha"));

    public List<User> getUser(String containName) {
        Password password = new Password();
        password.setClave("123124124");
        return users.stream().filter(user -> user.getName().contains(containName)).collect(Collectors.toList());
    }

}
