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
				
				fw.write("<html lang=\"en_us\">\n"
						+ 	"<head>\n"
						+ 		"<title> GeneratedFile </title>\n"
						+ 	"</head>\n"
						+ 	"<script>\n"
						+ "const MongoClient = require('mongodb').MongoClient;\r\n" + 
						"const url = 'mongodb://db.beursfolder.mrfriendly.uk:27017'\r\n" + 
						"const dbName = 'beursfolder'\r\n" + 
						"const client = new MongoClient(url)\r\n" + 
						"\r\n" + 
						"const companyName = '" + companyName + "'\r\n" + 
						"const phoneNumber = '" + phoneNumber + "'\r\n" + 
						"const token = '" + token +"'\r\n" + 
						"const optionUrl = '"  + optionURL + "'\r\n" +
						"const firstName = '" + firstName +"'\r\n" + 
						"const lastName = '" + surName + "'\r\n" +
						"\r\n" + 
						"const insertRedir = (db, callback) => {\r\n" + 
						"  find({token:token}, (err, res) => {\r\n" + 
						"    if(!res||res == null){\r\n" + 
						"      collection.insert(\r\n" + 
						"        {\"token\" : token, \"data\":\r\n" + 
						"            [\r\n" + 
						"              {\r\n" + 
						"                companyName:companyName,\r\n" + 
						"                phoneNumber:phoneNumber,\r\n" + 
						"                firstName:'fname',\r\n" + 
						"                lastName:'lname',\r\n" + 
						"                optionUrl:optionUrl\r\n" + 
						"              }\r\n" + 
						"            ]\r\n" + 
						"        }\r\n" + 
						"      )\r\n" + 
						"    } else{\r\n" + 
						"      const info = res.info\r\n" + 
						"      info.push([\r\n" + 
						"        {\r\n" + 
						"          companyName:companyName,\r\n" + 
						"          phoneNumber:phoneNumber,\r\n" + 
						"          firstName:'fname',\r\n" + 
						"          lastName:'lname',\r\n" + 
						"          optionUrl:optionUrl\r\n" + 
						"        }\r\n" + 
						"      ])\r\n" + 
						"      collection.update(\r\n" + 
						"        {token:token}, {$set:{\"info\":info}}\r\n" + 
						"      )\r\n" + 
						"    }\r\n" + 
						"  })\r\n" + 
						"  callback\r\n" + 
						"}\r\n" + 
						"\r\n" + 
						"client.connect(err => {\r\n" + 
						"  if(!(!err||err == null)){\r\n" + 
						"    console.log('successful connect')\r\n" + 
						"    const db = client.db(dbName)\r\n" + 
						"    insertRedir(db, function(){\r\n" + 
						"        client.close()\r\n" + 
						"    })\r\n" + 
						"\r\n" + 
						"  }\r\n" + 
						"\r\n" + 
						"})"
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
