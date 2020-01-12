package nl.thedutchmc.beursfolderserver.dataHandler;

import java.io.File;
import java.io.FileWriter;

import nl.thedutchmc.beursfolderserver.BeursFolderServer;

public class CreateJSFile {

	public void creatFile(String fileName, String firstName, String surName, String companyName, String phoneNumber, String token, int optionNumber, String optionURL) {
		String rootPath = BeursFolderServer.WEBSERVER_PATH;
		String redirectPath = rootPath + BeursFolderServer.REDIRECT_PATH;
		
		System.out.println("Redirect to: " + optionURL);
		
		
		String filePath = redirectPath + fileName + ".html";
		File redirectFile = new File(filePath);
		
		if(!(redirectFile.exists())) {
			try {
				redirectFile.createNewFile();
				
				FileWriter fw = new FileWriter(filePath);
				
				fw.write("<html><head>\r\n" + 
						"		<title> test </title>\r\n" + 
						"	</head>\r\n" + 
						"	<body>\r\n" + 
						"		<p>Mr.Friendly Redirect Page, you will be redirected in a couple seconds!</p>\r\n" + 
						"	<script>\r\n" + 
						" function autoSubmit(){\r\n" + 
						"  var form = document.createElement(\"FORM\")\r\n" + 
						"  form.name = 'dbStuff'\r\n" + 
						"  var companyName = \"" + companyName + "\"\r\n" + 
						"  var phoneNumber = \"" + phoneNumber + "\"\r\n" + 
						"  var token = \"" + token + "\"\r\n" + 
						"  var optionUrl = \"" + optionURL +"\"\r\n" + 
						"  var firstName = \"" + firstName +"\"\r\n" + 
						"  var lastName = \"" + surName + "\"\r\n" + 
						"  let optionID = \""+ optionNumber + "\"\r\n" + 
						"\r\n" + 
						"  form.method = \"POST\"\r\n" + 
						"  form.action = \"\"\r\n" + 
						"\r\n" + 
						"  element1 = document.createElement(\"INPUT\")\r\n" + 
						"  element2 = document.createElement(\"INPUT\")\r\n" + 
						"  element3 = document.createElement(\"INPUT\")\r\n" + 
						"  element4 = document.createElement(\"INPUT\")\r\n" + 
						"  element5 = document.createElement(\"INPUT\")\r\n" + 
						"  element6 = document.createElement(\"INPUT\")\r\n" + 
						"  element7 = document.createElement(\"INPUT\")\r\n" + 
						"\r\n" + 
						"  element1.value=companyName;\r\n" + 
						"  element1.name=\"companyName\";\r\n" + 
						"  element1.type=\"TEXT\"\r\n" + 
						"  element1.id='companyName'\r\n" + 
						"  form.appendChild(element1);\r\n" + 
						"\r\n" + 
						"  element2.value=phoneNumber;\r\n" + 
						"  element2.name=\"phoneNumber\";\r\n" + 
						"  element2.type=\"TEXT\"\r\n" + 
						"element2.id='phoneNumber'\r\n" + 
						"  form.appendChild(element2);\r\n" + 
						"\r\n" + 
						"  element3.value=token;\r\n" + 
						"  element3.name=\"token\";\r\n" + 
						"  element3.type=\"HIDDEN\"\r\n" + 
						"  element3.id='token'\r\n" + 
						"  form.appendChild(element3);\r\n" + 
						"\r\n" + 
						"  element4.value=optionUrl.toString();\r\n" + 
						"  element4.name=\"optionUrl\";\r\n" + 
						"  element4.type=\"HIDDEN\"\r\n" + 
						"  element4.id='optionUrl'\r\n" + 
						"  form.appendChild(element4);\r\n" + 
						"\r\n" + 
						"  element5.value=firstName;\r\n" + 
						"  element5.name=\"firstName\";\r\n" + 
						"  element5.type=\"HIDDEN\"\r\n" + 
						"  form.appendChild(element5);\r\n" + 
						"\r\n" + 
						"  element6.value=lastName;\r\n" + 
						"  element6.name=\"lastName\";\r\n" + 
						"  element6.type=\"HIDDEN\"\r\n" + 
						"  element6.id='lastName'\r\n" + 
						"  form.appendChild(element6);\r\n" + 
						"\r\n" + 
						"  element7.value = optionID\r\n" + 
						"  element7.name = \"optionID\"\r\n" + 
						"  element7.type = \"HIDDEN\"\r\n" + 
						"  element7.id='optionID'\r\n" + 
						"  form.appendChild(element7)\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"  document.body.appendChild(form)\r\n" + 
						"  form.submit()\r\n" + 
						"  console.log(body)\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						"autoSubmit() \r\n" + 
						"</script><form name=\"dbStuff\" method=\"POST\" action=\"\"><hidden></hidden><hidden></hidden><hidden></hidden><hidden></hidden><hidden></hidden><hidden></hidden><hidden></hidden></form>\r\n" + 
						"</body></html>");
				
				fw.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
