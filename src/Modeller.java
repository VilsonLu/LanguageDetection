import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.aliasi.lm.LanguageModel;
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory;
import com.aliasi.tokenizer.TokenizerFactory;


public class Modeller {

	public void createUnigramModel(List<String> filePaths, String fileSave){
		HashMap<String, Integer> model = new HashMap<String,Integer>();
		
		try {
			
			for(String filePath: filePaths){
			File fileDir = new File(filePath);
			
			BufferedReader in = new BufferedReader(
			   new InputStreamReader(
	                      new FileInputStream(fileDir), "UTF8"));
				
			String lineReader = null;
			while((lineReader = in.readLine()) != null){
				StringTokenizer tokenizer = new StringTokenizer(lineReader);
				while(tokenizer.hasMoreTokens()){
					String token = tokenizer.nextToken().toLowerCase();
					Integer count = 0;
					
					if((count = model.get(token)) != null)
						model.put(token, count+1);
					else{
						model.put(token, 1);
					}
					
				}
			}
			in.close();
			}
			// Save Model
			
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileSave), "UTF8"));
			
			for (Map.Entry<String, Integer> entry : model.entrySet())
			{
			    out.write(entry.getKey() + " " + entry.getValue() + "\n");
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
	}
}
