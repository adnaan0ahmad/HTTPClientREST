package com.rest.client.HTTPClient;

import java.io.IOException;

public class HttpClientDemo {

	public static void main(String[] args) {

		try {
			System.out.println(Client.getUser(2));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
