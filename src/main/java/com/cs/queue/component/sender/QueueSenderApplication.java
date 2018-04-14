package com.cs.queue.component.sender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class QueueSenderApplication {

	static String filePath = "E:\\workspaces\\testSpace";
	private static final Logger LOGGER = LoggerFactory.getLogger(QueueSenderApplication.class);
	
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext con = SpringApplication.run(QueueSenderApplication.class, args);
		Sender sender = con.getBean(Sender.class);
		processFiles(sender);
	}
	
	private static void processFiles(Sender sender) throws InterruptedException{
		File dir = new File(filePath);
		File newName = null;
		String content = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		FileWriter fw = null;
		while(true){
			File[] files =dir.listFiles();
			for(File file : files){
				if(file.isFile()){
					newName = new File(filePath+"\\processed\\"+file.getName()+".done");
					try{
						Thread.sleep(200);
						fr = new FileReader(file);
						br = new BufferedReader(fr);
						fw = new FileWriter(newName);
						bw = new BufferedWriter(fw);
						while((content = br.readLine())!=null){
							bw.write(content);
						}
						bw.flush();
					} catch (IOException e) {
						LOGGER.error("Exception encountered in ProcessFile",e);
					}finally{
						try {
							bw.close();
							fw.close();
							br.close();
							file.delete();
						} catch (IOException e) {
							LOGGER.error("Exception encountered in ProcessFile Closing buffers",e);
						}
					}
					sender.send("test.q", newName.getAbsolutePath());		
				}
			}
		}
	}
}
