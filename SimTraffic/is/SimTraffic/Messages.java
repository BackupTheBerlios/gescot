package is.SimTraffic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	private static String BUNDLE_NAME = "is.SimTraffic.messages"; //$NON-NLS-1$

	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	Messages() {
		File fichero = new File(".\\SimTraffic.conf");
		try {
			FileReader fr = new FileReader(fichero);
			BufferedReader br = new BufferedReader(fr);
			String valorIdioma = br.readLine();
			if (valorIdioma.compareTo("Inglés") == 0) {
				BUNDLE_NAME = "is.SimTraffic.messagesen"; //$NON-NLS-1$

				RESOURCE_BUNDLE = ResourceBundle
						.getBundle(BUNDLE_NAME);
			}
		} catch (FileNotFoundException e) {
			try {
				fichero = new File("c:\\hlocal\\SimTraffic.conf");
				FileReader fr = new FileReader(fichero);
				BufferedReader br = new BufferedReader(fr);
				String valorIdioma = br.readLine();
				if (valorIdioma.compareTo("Inglés") == 0) {
					BUNDLE_NAME = "is.SimTraffic.messagesen"; //$NON-NLS-1$

					RESOURCE_BUNDLE = ResourceBundle
							.getBundle(BUNDLE_NAME);
				}	
			} catch (FileNotFoundException f) {
				BUNDLE_NAME = "is.SimTraffic.messages"; //$NON-NLS-1$

				RESOURCE_BUNDLE = ResourceBundle
						.getBundle(BUNDLE_NAME);				
			} catch (IOException e) {
				BUNDLE_NAME = "is.SimTraffic.messages"; //$NON-NLS-1$

				RESOURCE_BUNDLE = ResourceBundle
						.getBundle(BUNDLE_NAME);
			}
		} catch (IOException e) {
			BUNDLE_NAME = "is.SimTraffic.messages"; //$NON-NLS-1$

			RESOURCE_BUNDLE = ResourceBundle
					.getBundle(BUNDLE_NAME);
		}
		
		
		
	}

	public static String getString(String key) {
		// TODO Auto-generated method stub
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
