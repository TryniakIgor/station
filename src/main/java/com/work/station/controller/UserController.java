package com.work.station.controller;

import com.work.station.dto.UserDTO;
import com.work.station.model.User;
import com.work.station.repository.UserRepo;
import com.work.station.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${url}")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;
    private final UserRepo userRepo;

    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getUsers(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        Page<User> pageTuts = userRepo.findAll(paging);

        Map<String, Object> response = new HashMap<>();
        response.put("users", userService.getUsers(paging));
        response.put("currentPage", pageTuts.getNumber());
        response.put("totalItems", pageTuts.getTotalElements());
        response.put("totalPages", pageTuts.getTotalPages());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/user/{name}")
    public ResponseEntity<UserDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok().body(userService.getUser(name));
    }

    @PutMapping("/user/{name}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String name, @RequestBody User user) {
        return ResponseEntity.ok().body(userService.updateUser(name, user));
    }

    @PostMapping("/user")
    public ResponseEntity<UserDTO> saveUsers(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @DeleteMapping("/user/{name}")
    public ResponseEntity<?> deleteByName(@PathVariable String name) {
        userService.deleteUser(name);
        return  ResponseEntity.noContent().build();
    }
}
