package com.example.loginsystem;

import com.example.loginsystem.entities.Role;
import com.example.loginsystem.helpers.AppConstant;
import com.example.loginsystem.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class LoginsystemApplication implements CommandLineRunner {
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(LoginsystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try{

			String encode = passwordEncoder.encode("Ajay");
			System.out.println(encode);
			Role user = new Role();
			user.setId(AppConstant.ROLE_USER_ID);
			user.setName(AppConstant.ROLE_USER);

			Role admin = new Role();
			admin.setId(AppConstant.ROLE_ADMIN_ID);
			admin.setName(AppConstant.ROLE_ADMIN);
			List<Role> roleList = List.of(user, admin);
			List<Role> roles = this.roleRepo.saveAll(roleList);
			System.out.println(roles);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
}
