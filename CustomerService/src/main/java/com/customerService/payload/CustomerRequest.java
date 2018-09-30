package com.customerService.payload;
import javax.validation.constraints.*;

public class CustomerRequest {

	@NotNull
	private Long idCustomer;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotNull
    @NotBlank
    private String oldPassword;
    
    
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;

   
    public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getOldPassword() { return this.oldPassword; }

	public void setOldPassword(String oldPassword) { this.oldPassword = oldPassword; }

}
