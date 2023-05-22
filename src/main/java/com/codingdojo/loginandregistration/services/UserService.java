package com.codingdojo.loginandregistration.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.loginandregistration.models.LoginUser;
import com.codingdojo.loginandregistration.models.User;
import com.codingdojo.loginandregistration.repositories.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo UserRepo;

	public List<User> allItems() {
		return UserRepo.findAll();
	}

	public User createOrUpdate(User User) {
		return UserRepo.save(User);
	}

	public User findUserById(long id) {
		return UserRepo.findById(id).orElse(null);
	}

	public void deleteItem(Long id) {
		UserRepo.deleteById(id);
	}

	public User register(User newUser, BindingResult result) {

		Optional<User> potentialUser = UserRepo.findByEmail(newUser.getEmail());

		if (potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Email already exists!");
		}

		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "The Confirm Password must match Password!");
		}

		if (result.hasErrors()) {
			return null;
		}

		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);

		return this.createOrUpdate(newUser);
	}

	public User login(LoginUser newLogin, BindingResult result) {

		Optional<User> potentialUser = UserRepo.findByEmail(newLogin.getEmail());

		if (!potentialUser.isPresent()) {
			result.rejectValue("email", "Matches", "Invalid Email/Password!");
			return null;
		}

		User thisUser = potentialUser.get();

		if (!BCrypt.checkpw(newLogin.getPassword(), thisUser.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Email/Password!");
		}

		if (result.hasErrors()) {
			return null;
		}

		return thisUser;
	}
}
