package com.example.CuotasGym;

import com.example.CuotasGym.servicios.CuotaServicio;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@SpringBootApplication
public class CuotasGymApplication {
    

	public static void main(String[] args) {
            
            
		SpringApplication.run(CuotasGymApplication.class, args);
                      
                
	}

}
