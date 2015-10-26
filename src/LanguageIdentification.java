import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.StringTokenizer;


public class LanguageIdentification {
	
	private HashMap<String,Integer> bikolModel;
	private HashMap<String,Integer> cebuanoModel;
	private HashMap<String,Integer> hiligaynonModel;
	private HashMap<String,Integer> ilocanoModel;
	private HashMap<String,Integer> kapampanganModel;
	private HashMap<String,Integer> pangasinenseModel;
	private HashMap<String,Integer> tagalogModel;
	private HashMap<String,Integer> warayModel;
	
	
	
	private static int BIKOL = 0;
	private static int CEBUANO = 1;
	private static int HILIGAYNON = 2;
	private static int ILOCANO = 3;
	private static int KAPAMPANGAN = 4;
	private static int PANGASINENSE = 5;
	private static int TAGALOG = 6;
	private static int WARAY = 7;
	
	
	public LanguageIdentification(){
		initModel();
		
	}
	
	private void initModel(){
		bikolModel = readModel(".\\Models\\Bikol_Unigram.txt");
		cebuanoModel = readModel(".\\Models\\Cebuano_Unigram.txt");
		hiligaynonModel = readModel(".\\Models\\Hiligaynon_Unigram.txt");
		ilocanoModel = readModel(".\\Models\\Ilocano_Unigram.txt");
		kapampanganModel = readModel(".\\Models\\Kapampangan_Unigram.txt");
		pangasinenseModel = readModel(".\\Models\\Pangasinense_Unigram.txt");
		tagalogModel = readModel(".\\Models\\Tagalog_Unigram.txt");
		warayModel = readModel(".\\Models\\Waray_Unigram.txt");
	}
	
	private HashMap<String,Integer> readModel(String path){
		HashMap<String,Integer> model = new HashMap<String,Integer>();
		
		File fileDir = new File(path);
		
		BufferedReader in;
		try {
			in = new BufferedReader(
			   new InputStreamReader(
			              new FileInputStream(fileDir), "UTF8"));
			String lineReader = null;
			while((lineReader = in.readLine()) != null){
				String[] split = lineReader.split(" ");
				model.put(split[0].toLowerCase(), Integer.valueOf(split[1]));
			}
			in.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		return model;
	}
	
	public String identifyLanguage(String sentence){
		int[] scores = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		StringTokenizer tokenizer = new StringTokenizer(sentence);
		while(tokenizer.hasMoreTokens()){
			String token = tokenizer.nextToken().toLowerCase();
		
			scores[BIKOL] += bikolModel.getOrDefault(token, 0);
			scores[CEBUANO] += cebuanoModel.getOrDefault(token, 0);
			scores[HILIGAYNON] += hiligaynonModel.getOrDefault(token, 0);
			scores[ILOCANO] += ilocanoModel.getOrDefault(token, 0);
			scores[KAPAMPANGAN] += kapampanganModel.getOrDefault(token, 0);
			scores[PANGASINENSE] += pangasinenseModel.getOrDefault(token, 0);
			scores[TAGALOG] += tagalogModel.getOrDefault(token, 0);
			scores[WARAY] += warayModel.getOrDefault(token, 0);			
		}
		return getLanguageId(getHighestScore(scores));
	}
	
	private String getLanguageId(int id){
		switch(id){
			case 0: return "Bikol";
			case 1: return "Cebuano";
			case 2: return "Hiligaynon";
			case 3: return "Ilocano";
			case 4: return "Kapampangan";
			case 5: return "Pangasinense";
			case 6: return "Tagalog";
			case 7: return "Waray";
			default: return "invalid language";
		}
	}
	
	private int getHighestScore(int[] scores){
		int index = -1;
		int high = 0;
		for(int i=0; i<scores.length; i++){
			if(scores[i] > high){
				index = i;
				high = scores[i];
			}
		}
		
		return index;
	}
}
