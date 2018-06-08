package com.janiny.tools.test.exec;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.janiny.tools.test.exec.dto.InputFile;
import com.janiny.tools.test.exec.dto.OutReport;
import com.janiny.tools.test.exec.entity.Cliente;
import com.janiny.tools.test.exec.entity.Venda;
import com.janiny.tools.test.exec.entity.Vendedor;

public class Main {

	private static OutReport outReport;

	public static void main(String[] args) {
		outReport = new OutReport();

		Scanner scanner = new Scanner(System.in);
		String inputFolder = requisitarInputFolder(scanner);
		String outputFolder = requisitarOutputFolder(scanner);
		scanner.close();

		procurarArquivos(inputFolder);
		gerarOutFiles(outputFolder);

	}

	private static void gerarOutFiles(String outputFolder) {
		for (InputFile inputFile : outReport.getFiles()) {
			gerarOutFile(outputFolder, inputFile);
		}

	}

	private static void gerarOutFile(String outputFolder, InputFile inputFile) {

		try {
			Path inputFolderPath = Paths.get(outputFolder + "/" + inputFile.getFileName() + ".proc").toAbsolutePath();
			if(Files.notExists(inputFolderPath)){
				Files.createFile(inputFolderPath);
			}
			try (BufferedWriter writer = Files.newBufferedWriter(inputFolderPath)) {
				writer.write(inputFile.gerarOutputString());
			}

		} catch (IOException e) {
			System.out.print("Houve um erro ao tentar criar arquivo de output. " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static String requisitarInputFolder(Scanner scanner) {
		String inputFolder;
		System.out.print("Por favor informe o diretorio de arquivos (Valor default /dados/in): ");
		inputFolder = scanner.nextLine();

		if (inputFolder == null || "".equals(inputFolder)) {
			inputFolder = "/dados/in";
		}
		return inputFolder;
	}

	private static String requisitarOutputFolder(Scanner scanner) {
		String outputFolder;
		System.out.print("Por favor informe o diretorio de output (Valor default /dados/out): ");
		outputFolder = scanner.nextLine();

		if (outputFolder == null || "".equals(outputFolder)) {
			outputFolder = "/dados/out";
		}
		return outputFolder;
	}

	private static void procurarArquivos(String inputFolder) {
		Path inputFolderPath = Paths.get(inputFolder);
		try {
			Files.walk(inputFolderPath).filter(s -> s.toString().endsWith(".dat")).map(Path::toAbsolutePath)
					.forEach(filepath -> lerArquivo(filepath));
		} catch (Exception e) {
			System.out.print("Houve um erro ao tentar ler a pasta de arquivos. " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void lerArquivo(Path filepath) {
		File fileSource = filepath.toFile();

		InputFile inputFile = new InputFile(fileSource.getName());
		outReport.getFiles().add(inputFile);

		try (Scanner scanner = new Scanner(fileSource)) {

			while (scanner.hasNext()) {
				cadastrarLinha(scanner.nextLine(), inputFile);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void cadastrarLinha(String nextLine, InputFile inputFile) {
		String[] valores = nextLine.split(";");

		if (valores != null) {
			if ("001".equals(valores[0])) {
				Vendedor vendedor = new Vendedor(valores[1], valores[2], Double.valueOf(valores[3]));
				inputFile.getVendedores().add(vendedor);
			} else if ("002".equals(valores[0])) {
				Cliente cliente = new Cliente(valores[1], valores[2], valores[3]);
				inputFile.getClientes().add(cliente);
			} else {
				Venda venda = new Venda(Integer.valueOf(valores[1]), Integer.valueOf(valores[2]),
						Integer.valueOf(valores[3]), Double.valueOf(valores[4]), valores[5]);
				inputFile.getVendas().add(venda);
			}
		}
	}

}
