package com.ikytus.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletResponse;

public class UtilCSV {
	private static final String DELIMITER = ";";

	public static StringBuilder createCSV(List<?> dados) {
		StringBuilder csvFile = new StringBuilder();
		return initProcess(csvFile, dados);
	}

	private static StringBuilder initProcess(StringBuilder csvFile, List<?> dados) {
		getAttributesNames(dados.get(0)).stream().forEach(header -> insertDataCollunm(csvFile, header));
		csvFile.append("\r\n");
		return addRecord(csvFile, dados);
	}

	private static StringBuilder addRecord(StringBuilder csvFile, List<?> dados) {
		dados.stream().forEach(obj -> {
			getAttributesNames(obj).stream().forEach(attribut -> {
				Method metodo = getMethodByString(obj, attribut);
				try {
					insertDataCollunm(csvFile, (String) metodo.invoke(obj));
				} catch (InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
					System.out.println("ERRO AO TENTAR ADICIONAR O REGISTRO!");
				}
			});
			csvFile.append("\r\n");
		});
		UtilFile.gravarArquivo(csvFile, "./src/main/resources/static/test.csv");
		return csvFile;
	}

	public static void insertDataCollunm(StringBuilder csvFile, String data) {
		csvFile = data.isEmpty() ? csvFile.append(DELIMITER) : csvFile.append(data).append(DELIMITER);
	}

	public static List<String> getAttributesNames(Object obj) {
		List<String> attributs = new ArrayList<>();
		Stream.of(obj.getClass().getDeclaredFields()).forEach(field -> attributs.add(field.getName()));
		return attributs;
	}

	public static Method getMethodByString(Object obj, String attribut) {
		Method method = null;
		try {
			Class<?> classe = Class.forName(obj.getClass().getName());
			method = classe.getDeclaredMethod("get" + StringUtils.capitalize(attribut));
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
			System.out.println("ERRO AO TENTAR OBTER O MÉTODO: " + attribut);
		}
		return method;
	}
	
	public static void exportCSV (StringBuilder data, HttpServletResponse response, String nomeRelatorio) throws IOException {
		byte[] relatorio = data.toString().getBytes();
		UtilFile.exportFile(relatorio, response, nomeRelatorio, "csv");
	}
}
