package com.ikytus.util;

import java.io.FileWriter;
import java.io.IOException;

public class UtilFile {
	
	public static void gravarArquivo(StringBuilder file, String path) {
		try {
			FileWriter writer = new FileWriter(path);
			writer.append(file);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
