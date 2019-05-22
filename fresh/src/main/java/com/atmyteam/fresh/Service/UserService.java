package com.atmyteam.fresh.Service;
import com.atmyteam.fresh.Mode.Response;
import com.atmyteam.fresh.Mode.User;

public interface UserService {
     User getUser(User user);
     User createUser(User user);
     Response upUser(User user);
}
