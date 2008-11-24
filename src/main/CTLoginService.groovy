//package com.apress.bgg.services

import org.jdesktop.swingx.auth.LoginService

class CTLoginService extends LoginService {
	def name
	def password
	
	boolean authenticate(String name, char[] password, String server) {
		// This is where you would hook into a security system
		this.name = name
		this.password = password
		return true
	}	
}