package com.pratham.blogapp;

import com.pratham.blogapp.Config.AppConstants;
import com.pratham.blogapp.Entity.Role;
import com.pratham.blogapp.Repository.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppBackendApplication implements CommandLineRunner  {

	public static void main(String[] args)
	{
		SpringApplication.run(BlogAppBackendApplication.class, args);
	}



	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		try{
			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");

			Role role2 = new Role();
			role2.setId(AppConstants.ADMIN_USER);
			role2.setName("ADMIN_USER");

			List<Role>roles=List.of(role1,role2);

			List<Role>result=roleRepo.saveAll(roles);

			result.forEach(r->{
				System.out.println(r.getName());
			});

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
