import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Driver {
	public static void main(String[] args){
//		Modeller modeller = new Modeller();
//		
//		List<String> fileResources = new ArrayList<String>();
//		fileResources.add(".\\Resources\\Waray_Literary_Text.txt");
//		fileResources.add(".\\Resources\\Waray_Religious_Text.txt");
//		
//		String fileSave = ".\\Models\\Waray_Unigram.txt";
//		modeller.createUnigramModel(fileResources,fileSave);
		String input = "";
		System.out.print("Enter a sentence: ");
		Scanner s = new Scanner(System.in);
		input = s.nextLine();
		LanguageIdentification identifier = new LanguageIdentification();
		System.out.println("Language: " + identifier.identifyLanguage(input));
		s.close();
		
	}
}
