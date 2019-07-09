package com.example.tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		List<User> users = new ArrayList<>();

		users = collectUsers();

		GDGCadastro gdg = new GDGCadastro("insertURLHere");
		try {
			gdg.setUp();

			System.out.println(users.size());
			for (User u : users) {
				gdg.testGDGCadastro(u);
				System.out.println("Usuário: " + u.nome + " cadastrado com sucesso.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public static List<User> collectUsers() {

		List<User> users = new ArrayList<>();
		List<String> lines = readAnyFile();

		for (String line : lines) {
			User u = new User();

			String[] data = line.split(",");

			if (data.length == 3) {
				String nome = data[1];
				String sobrenome = nome.substring(nome.indexOf(" ") + 1);
				nome = nome.substring(0, nome.indexOf(" "));
				String email = data[0];
				String telefone = data[2];
				String ddd = telefone.substring(0, 2);
				telefone = telefone.substring(2);

				u.nome = nome;
				u.sobrenome = sobrenome;
				u.email = email;
				u.telefone = telefone;
				u.ddd = ddd;

				users.add(u);
			}

		}

		return users;

	}

	public static List<String> readAnyFile() {
		String filename = "gdg.csv";

		List<String> lines = new ArrayList<String>();
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), Charset.forName("UTF-8").newDecoder()));
			// reader = new BufferedReader(new FileReader(filename));

			String line = reader.readLine();

			
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}

			reader.close();
			return lines;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in file: " + filename);
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();

				}
			}
		}
		

		return lines;
	}
}
