package com.example.demo.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
	@Column(unique = true, updatable = false, nullable = false,length = 40)
	private String userId;
	
	
	@NotNull
	@Length(max = 50, min = 3)
	private String firstName;
	
	@NotNull
	@Length(max = 50, min = 3)
	private String lastName;
	
	@Column(unique = true, length = 50)
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	
	@NotNull
	@Size(max = 11)
	private String phoneNumber;
	
	@NotNull
	private Boolean userIsActive;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private VerificationModel verificationToken;

	
	public User(@Size(min = 3) @NotNull String firstName, @Size(max = 3) @NotNull String lastName,
			@NotNull String email, @NotNull String password, @NotNull String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
}
