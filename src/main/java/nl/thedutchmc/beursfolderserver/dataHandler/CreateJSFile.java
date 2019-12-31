package nl.thedutchmc.beursfolderserver.dataHandler;

import java.io.File;
import java.io.FileWriter;

import nl.thedutchmc.beursfolderserver.BeursFolderServer;

public class CreateJSFile {

	public void creatFile(String fileName, String companyName, String phoneNumber, String token, int optionNumber, String optionURL) {
		String rootPath = BeursFolderServer.WEBSERVER_PATH;
		String redirectPath = rootPath + BeursFolderServer.REDIRECT_PATH;
		String siteDomain = BeursFolderServer.SITE_DOMAIN;
		
		String redirectTo = siteDomain + BeursFolderServer.REDIRECT_PATH + optionURL;
		System.out.println("Redirect to: " + redirectTo);
		
		
		String filePath = redirectPath + fileName + ".html";
		File redirectFile = new File(filePath);
		
		if(!(redirectFile.exists())) {
			try {
				redirectFile.createNewFile();
				
				FileWriter fw = new FileWriter(filePath);
				
				fw.write("<html lang=\"en_us\">\n"
						+ 	"<head>\n"
						+ 		"<title> GeneratedFile </title>\n"
						+ 	"</head>\n"
						+ 	"<script>\n"
						+		"var companyName = \"" + companyName + "\"\n"
						+		"var phoneNumber = \"" + phoneNumber + "\"\n"
						+		"var token = \"" + token + "\"\n"
						+		"var optionURL = \"" + optionURL + "\"\n"
						+		"window.location = \"" + redirectTo + "\";"
						+	"</script>\n"
						+ 	"<body\n>"
						+ 		"<p> test </p>\n"
						+ 	"</body\n>"
						+ "</html\n>");
				
				fw.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
