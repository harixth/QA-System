


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class getInput {

	private String path;
	
	public getInput(String file_path){
		path = file_path;
	}
	
	public String[] OpenFile() throws IOException{
		FileReader readfile = new FileReader(path);
		BufferedReader Readtext = new BufferedReader(readfile);	
		int linenumber = readLines( );
			String[] text = new String[linenumber];
		int i;
		for (i=0;i<linenumber;i++) {
			text[i] = Readtext.readLine();
		}
		Readtext.close();
		
		return text;
	}
	
	public int readLines() throws IOException {
		FileReader file_to_read = new FileReader(path);
		BufferedReader br = new BufferedReader(file_to_read);
		String line;
		int linenumber = 0;
		while (( line = br.readLine()) != null) {
			linenumber++;
		}
		br.close();
		
		return linenumber;
	}
}
