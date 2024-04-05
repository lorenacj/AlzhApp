package com.example.demo.entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Carer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank(message = "Name can not be empty.")
	@Length(min = 2, max = 20, message = "Name length must be between 2 and 20 characters")
	private String name;

	@NotBlank(message = "Last name can not be empty")
	@Length(min = 5, max = 50, message = "Lastname length must be between 5 and 50 characters")
	private String lastname;

	@NotBlank(message = "Telephone can not be empty")
	@Pattern(regexp = "^[1-9]\\d{8}$", message = "Invalid format, enter a number with 9 digits")
	private String telephone;

	@NotBlank(message = "Role can not be null")
	private String role;

	// Username will be passport number
	@Column(unique = true)
	@NotBlank(message = "User can not be empty, please enter your passport ID")
	@Pattern(regexp = "\\d{8}[A-HJ-NP-TV-Z]")
	private String username;
	@NotBlank(message = "Password can not be empty")
	@Length(min = 5, max = 50, message = "Password length have to be between 5 and 50")
	private String password;
	private String token;
	private boolean enabled;
	private boolean deleted;

	@Override
	public String toString() {
		return "Carer [id=" + id + ", name=" + name + ", lastname=" + lastname + ", telephone=" + telephone + ", role="
				+ role + ", username=" + username + ", password=" + password + ", token=" + token + ", enabled="
				+ enabled + "]";
	}

}
