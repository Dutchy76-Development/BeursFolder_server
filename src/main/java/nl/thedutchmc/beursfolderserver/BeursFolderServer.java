package nl.thedutchmc.beursfolderserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import nl.thedutchmc.beursfolderserver.socketHandler.ServerSocketHandler;

public class BeursFolderServer {

	public static int SERVER_PORT;
	public static String WEBSERVER_PATH, REDIRECT_PATH, SITE_DOMAIN;

	public static void main (String[] args) {
				
		//Check if the path to the configuration file was given
		if(args[0] != null) {
			System.out.println("Reading config file...");
			
			String confPath = args[0];
			BeursFolderServer bfs = new BeursFolderServer();
			bfs.readConfig(confPath);
			
			System.out.println("Starting server...");
			ServerSocketHandler ssh = new ServerSocketHandler();
			ssh.startServer();
			
		} else {
			System.err.println("Configuration Path not given, exiting!");
			System.exit(1);
		}
		
	}
	
	public void readConfig(String confPath) {
		String appConfigPath = confPath + "server_app.properties";
		
		File appPropertiesFile = new File(appConfigPath);
		
		if(!(appPropertiesFile.exists())) {
			System.out.println("server_app.properties file doesn't exist, creating!");
			
			try {
				appPropertiesFile.createNewFile();
				
				FileWriter fw = new FileWriter(appPropertiesFile, true);
				
				fw.write("#BeursFolder Server configuration file\n" +
				"# Do not edit below this line\n");
				fw.write("appVersion = 1.0\n\n");
				fw.write("# You can edit below this line.\n\n");
				
				fw.write("server_port = 8095\n\n");
				
				fw.write("#Where is the webserver located? E.g: /var/www/ \n");
				fw.write("#DO NOT FORGET THE TRAILING SLASH!\n");
				fw.write("webserver_path = \n\n");
				
				fw.write("#What sub folder do we use for the redirect files? E.g: redirects/ \n");
				fw.write("#THIS HAS TO MATCH WHAT IS SET ON THE CLIENT!\n");
				fw.write("#DO NOT FORGET THE TRAILING SLASH!\n");
				fw.write("redirect_path = \n\n");
				
				fw.write("#What is the domain name used? Include http/https! E.g: https://example.com/ \n");
				fw.write("#DO NOT FORGET THE TRAILING SLASH! \n");
				fw.write("siteDomain = \n");
				
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(appPropertiesFile.exists()) {
			Properties appProps = new Properties();
			
			try {
				appProps.load(new FileInputStream(appConfigPath));
				
				String appVersion = appProps.getProperty("appVersion");
				
				if(!(appProps.getProperty("server_port").equals(""))) {
					SERVER_PORT = Integer.valueOf(appProps.getProperty("server_port"));

				} else {
					System.err.println("Server port is not set, exiting!");
					System.exit(1);
					
				}
				
				if(!(appProps.getProperty("webserver_path").equals(""))) {
					WEBSERVER_PATH = appProps.getProperty("webserver_path");
				} else {
					System.err.println("Webserver path not set, exiting!");
					System.exit(1);
				}
				
				REDIRECT_PATH = appProps.getProperty("redirect_path");
				if(REDIRECT_PATH.equals("")) {
					System.out.println("Redirect Path not set, using root directory (UNADVISED!)");
				}
				
				SITE_DOMAIN = appProps.getProperty("siteDomain");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
