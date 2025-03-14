package com.example.areplab6.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.areplab6.model.User;
import com.example.areplab6.repository.UserRepository;

class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindByUsername_UserExists() {
		String username = "testUser";
		User mockUser = new User(username, "encryptedPassword");
		when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

		Optional<User> result = userService.findByUsername(username);

		assertTrue(result.isPresent());
		assertEquals(username, result.get().getUsername());
	}

	@Test
	void testFindByUsername_UserNotFound() {
		String username = "notExist";
		when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

		Optional<User> result = userService.findByUsername(username);

		assertFalse(result.isPresent());
	}

	@Test
	void testRegisterUser_Success() {
		String username = "newUser";
		String password = "password";
		when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

		userService.registerUser(username, password);

		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	void testRegisterUser_UserAlreadyExists() {
		String username = "existingUser";
		String password = "password";
		when(userRepository.findByUsername(username)).thenReturn(Optional.of(new User(username, "encodedPassword")));

		userService.registerUser(username, password);

		verify(userRepository, never()).save(any(User.class));
	}
}
