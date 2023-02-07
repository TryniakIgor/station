package com.work.station.service.implementation;

import com.work.station.dto.UserDTO;
import com.work.station.exeption.EntityAlreadyExist;
import com.work.station.exeption.ResourseNotFoundExeption;
import com.work.station.mapper.UserMapper;
import com.work.station.model.User;
import com.work.station.repository.UserRepo;
import com.work.station.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO saveUser(User user) {
        log.info("Saving new user {} to DB", user.getName());

        Optional<User> existingUser = Optional.ofNullable(userRepo.findByNameAndIsDeletedIsFalse(user.getName()));
        existingUser.ifPresentOrElse(
                (value) -> {
                    throw new EntityAlreadyExist("User", user.getName());
                }, () -> userRepo.save(user)
        );
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return UserMapper.toDTO(userRepo.save(user));
    }

    @Override
    public UserDTO getUser(String name) {
        log.info("Fetching user {}", name);
        User user = userRepo.findByNameAndIsDeletedIsFalse(name);
        if (user != null)
            return UserMapper.toDTO(user);
        else throw new ResourseNotFoundExeption("User", name);
    }

    @Override
    public List<UserDTO> getUsers(Pageable pageable) {
        log.info("Fetching all users");
        return userRepo.findAll(pageable).stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(String name, User user) {
        log.info("Update user {} ", name);

        User oldUser = Optional.ofNullable(userRepo.findByNameAndIsDeletedIsFalse(name)).orElseThrow(() -> new ResourseNotFoundExeption("User", name));
        oldUser.setId(user.getId());
        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());

        return UserMapper.toDTO(oldUser);
    }

    @Override
    public void deleteUser(String name) {
        log.info("Change isDeleted user {} with true", name);
        Optional.ofNullable(userRepo.findByNameAndIsDeletedIsFalse(name)).orElseThrow(() -> new ResourseNotFoundExeption("User", name));
        userRepo.markAsDeleted(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByNameAndIsDeletedIsFalse(username);
        if (user == null) {
            log.error("User not found in DB");
            throw new UsernameNotFoundException("User not found in DB");
        } else {
            log.info("User found in DB:{}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
    }

}
