package com.ikytus.util;

import java.io.FileWriter;
import java.io.IOException;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

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
	
	public static void exportFile(byte[] relatorio, HttpServletResponse response, String nomeFileReport, String extensao)
			throws IOException {
						
		ServletOutputStream servletOutputStream = response.getOutputStream();
		response.setContentType("application/" + extensao);
		response.setContentLength(relatorio.length);
		response.addHeader("Content-Disposition", "attachment; filename=" + nomeFileReport + "." + extensao);
		servletOutputStream.write(relatorio, 0, relatorio.length);
		servletOutputStream.flush();
		servletOutputStream.close();
	}

}
