package org.mindcapture.Service;

import org.mindcapture.Pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findByPhone(String phone);
}
