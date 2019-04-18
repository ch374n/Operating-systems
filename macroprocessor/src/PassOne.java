// package com.nimbalkar.chetan;

import java.io.PrintWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PassOne extends MacroProcessor{
	private final String INPUT_FILE  = "input.asm";
	private final String OUTPUT_FILE = "output";
	private final String OUTPUT_TABLES = "tables";

	private PrintWriter writer1, writer2;
	private List<String> lines = new ArrayList<>();
	private int address = 0;

	public void readFile(String inputFile) {

		try {
            lines = Files.readAllLines(Paths.get(inputFile));
		} catch (Exception e) {
		    e.printStackTrace();
        }
	}

	public int getAddress() {
		return address++;
	}


	public void readMacro(int index) {
		int address = getAddress();
		StringTokenizer tokenizer = new StringTokenizer(lines.get(index));
		MNT.put(tokenizer.nextToken(), address);

		List<String> arguments = new ArrayList<>();
		
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if(!token.equals(",")) {
				arguments.add(token);
			}
		}

		lines.remove(index);

		String line = lines.get(index);
		while(!line.contains("MEND")) {

			for(int i = 0; i < arguments.size(); i++) {
				line = line.replaceAll(arguments.get(i),  "#" + (i + 1));
			}
			
			MDT.put(address, line);
			address = getAddress();
			lines.remove(index);
			line = lines.get(index);
		}
			MDT.put(address, "MEND");
			lines.remove(index);
	}

	public void storeTables(String outputFile, String outputTables) {
		try {
			writer1 = new PrintWriter(
					 new FileWriter(outputFile)
				);
			writer2 = new PrintWriter(
					  new FileWriter(outputTables)
				);

			lines.forEach((line)-> writer1.println(line));

			writer2.println("MNT");
			MNT.forEach((key, value)-> {
				writer2.println(key + "\t" + value);
			});

			writer2.println("MDT");
			MDT.forEach((key, value)-> {
				writer2.println(key + "\t+" + value);
			});


		} catch (Exception e) {
			e.printStackTrace();
		}
			writer1.close();
			writer2.close();
	}

	public void execute() {
		readFile(INPUT_FILE);
		int i = 0;
		while(i < lines.size()) {
			if(lines.get(i).contains("MACRO")) {
				lines.remove(i);
				readMacro(i);
				continue;
			}
			i++;
		}

		storeTables(OUTPUT_FILE, OUTPUT_TABLES);
	}

}

