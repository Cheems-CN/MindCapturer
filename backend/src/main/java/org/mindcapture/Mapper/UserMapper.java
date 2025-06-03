package org.mindcapture.Mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mindcapture.Pojo.User;

@Mapper
public class UserMapper {
    public User findByPhone(String phone) {
        return null;
    }
}
