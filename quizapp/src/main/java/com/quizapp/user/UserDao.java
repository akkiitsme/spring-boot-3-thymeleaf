package com.quizapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserBean,Integer> {

    boolean existsByEmail(String email);

    Optional<UserBean> findByEmail(String username);

    int countByRole(Role role);

    UserBean findByEmailAndPassword(String username , String password);

    Optional<UserBean> findByPhoneNo(String username);
}
