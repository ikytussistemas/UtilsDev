package com.ikytus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ikytus.entities.Group;
import com.ikytus.util.UtilCSV;
import com.ikytus.util.UtilFile;

@SpringBootApplication
public class UtilsDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilsDevApplication.class, args);
		
		/*
		 * Exemplo de uso do UtilCSV.
		 * */
		Group g1 = new Group("1","java", "teste de java");
		Group g2 = new Group("2","Android", "teste de android");

		List<Group> groups = new ArrayList<>();
		groups.addAll(Arrays.asList(g1, g2));
		
		StringBuilder file;
		file = UtilCSV.createCSV(groups);
		
		UtilFile.gravarArquivo(file, "./src/main/resources/static/test.csv");
	}

}
