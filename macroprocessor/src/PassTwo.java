// package com.nimbalkar.chetan;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PassTwo extends MacroProcessor{
	private final String INPUT_FILE = "output";
	private final String INPUT_TABLES  = "tables";
	private List<String> lines1 = new ArrayList<>(),
						 lines2 = new ArrayList<>();

	public void readFile(String inputFile, String inputTables) {
		try {
			lines1 = Files.readAllLines(Paths.get(inputFile));

			lines2 = Files.readAllLines(Paths.get(inputTables));

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void readTables() {
		int i = 1;

		String line = lines2.get(i);
		while(!line.contains("MDT"))  {
		 	String[] values = line.split("\t");
		 	MNT.put(values[0], Integer.parseInt(values[1]));
			i++;
			line = lines2.get(i);
		}

		i++;
		while(i < lines2.size()) {
			line = lines2.get(i);
			String[] values = line.split("\\+");
			MDT.put(Integer.parseInt(values[0].trim()), values[1]);
			i++;
		}
	}

	public void expandMacro(int index, int lineIndex, List<String> parameters) {
		String line = MDT.get(index);

		while(!line.contains("MEND")) {
			for(int i = 0; i < parameters.size(); i++) {
				line = line.replaceAll("#" + (i + 1), parameters.get(i));
			}
			lines1.add(lineIndex, line);
			index++;
			line = MDT.get(index);
			lineIndex++;
		}	
	}


	public void execute() {
		readFile(INPUT_FILE, INPUT_TABLES);
		readTables();

		for(int i = 0; i < lines1.size(); i++) {
			String line = lines1.get(i);

			for(String name : MNT.keySet()) {
				if(line.contains(name)) {

					StringTokenizer tokenizer = new StringTokenizer(line);

					String value = tokenizer.nextToken();
					if(MNT.containsKey(value)) {
						List<String> parameters = new ArrayList<>();

						while(tokenizer.hasMoreTokens()) {
							String token = tokenizer.nextToken();
							if(!token.equals(",")) {
								parameters.add(token);
							}
						}

						lines1.remove(i);
						expandMacro(MNT.get(value), i, parameters);
					}
				}
			}
		}

		lines1.forEach(System.out::println);
	}	
}