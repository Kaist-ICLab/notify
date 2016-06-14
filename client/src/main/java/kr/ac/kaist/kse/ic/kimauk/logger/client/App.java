package kr.ac.kaist.kse.ic.kimauk.logger.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

import org.apache.log4j.Logger;

public class App {
	
	final static Logger logger = Logger.getLogger(App.class);
	
	public static void main(String[] args) {
		try {
			logger.debug(InetAddress.getLocalHost().getHostAddress());
			logger.debug(InetAddress.getLocalHost().getHostName());
			logger.debug(System.getProperty("os.name"));
			logger.debug(System.getProperty("user.name"));

			Process process = // Runtime.getRuntime().exec("ps -e");
					Runtime.getRuntime().exec("tasklist /fi \"STATUS eq running\" /fo \"csv\"");

			BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream(), "EUC-KR"));
			String line;
			while ((line = input.readLine()) != null) {
				logger.debug(line);
			}
			input.close();

			input = new BufferedReader(new InputStreamReader(process.getErrorStream(), "EUC-KR"));
			while ((line = input.readLine()) != null) {
				logger.debug(line);
			}
			process.waitFor();

		} catch (IOException err) {
			err.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}