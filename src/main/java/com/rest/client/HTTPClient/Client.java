package com.rest.client.HTTPClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author Adnaan
 *
 */
public class Client {
	
	/**
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * 
	 * 
	 * 	1. 	Create a HTTP Client
	 * 	2. 	Create a HTTP Request (GET, PUT, POST, DELETE)
	 * 	3. 	To accept data from URL - add Header [ accept data in the form of JSON ]
	 * 	4. 	Execute request on client to get Response object
	 * 	5. 	Create HTTP Entity from Response Object which contains actual Java Entity 
	 * 	6. 	Convert this entity into string so that JSON Parser can convert it into java object
	 * 	7. 	Pass this string as a parameter to create JSON Array Object
	 * 	8. 	Use for each loop to iterate single JSON Object from the JSON Array
	 * 	9. 	Read Value method from Object Mapper class converts the JSON Object into java object directly
	 * 
	 */
	
	public static List<User> getUserList() throws JsonParseException, JsonMappingException, IOException{
		List<User> listOfUsers = new ArrayList<User>();
		
		HttpClient client = new DefaultHttpClient();
		HttpGet getReq = new HttpGet("http://jsonplaceholder.typicode.com/posts");
		getReq.addHeader("accept", "application/json");

		HttpResponse response = client.execute(getReq);

		HttpEntity entity = response.getEntity();
		String users = EntityUtils.toString(entity);
		
		ObjectMapper mapper = new ObjectMapper();
		JSONArray jsonarray = new JSONArray(users);
		
		for (Object ob : jsonarray) {
			JSONObject jsonObject = (JSONObject) ob;
			User u = mapper.readValue(jsonObject.toString(), User.class);
			listOfUsers.add(u);
		}

		return listOfUsers;
	}
	
	

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * 
	 * 
	 * 	1. 	Create a HTTP Client
	 * 	2. 	Create a HTTP Request (GET, PUT, POST, DELETE)
	 * 	3. 	To accept data from URL - add Header [ accept data in the form of JSON ]
	 * 	4. 	Execute request on client to get Response object
	 * 	5. 	Create HTTP Entity from Response Object which contains actual Java Entity 
	 * 	6. 	Convert this entity into string so that JSON Parser can convert it into java object
	 * 	7. 	Pass this string as a parameter to ReadValue method from Object Mapper to get  java object directly
	 * 
	 */
	
	public static User getUser(int id) throws ClientProtocolException, IOException {
		
		HttpClient client = new DefaultHttpClient();
		HttpGet getReq = new HttpGet("http://jsonplaceholder.typicode.com/posts/"+id);
		getReq.addHeader("accept", "application/json");

		HttpResponse response = client.execute(getReq);

		HttpEntity entity = response.getEntity();
		String user = EntityUtils.toString(entity);
		
		ObjectMapper mapper = new ObjectMapper();
		User u = mapper.readValue(user, User.class);
		
		return u;
	}
	
	public static boolean deleteUser(int id) throws ClientProtocolException, IOException {
		
		HttpClient client = new DefaultHttpClient();
		HttpDelete delreq = new HttpDelete("http://jsonplaceholder.typicode.com/posts/"+id);
		delreq.addHeader("accept", "application/json");
		client.execute(delreq);
		return true;
}
}
