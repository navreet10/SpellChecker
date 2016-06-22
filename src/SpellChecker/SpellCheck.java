package SpellChecker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SpellCheck {
	public static List<String> wordList = new LinkedList<String>();
	public static Set<String> wordSet = new HashSet<String>();
	public static int size;

	public static void main(String[] args) {
		BufferedReader bf = null;
		FileReader fr = null;
		Scanner in = null;
		try {
			in = new Scanner(System.in);
			File words = new File("/usr/share/dict/words");
			fr = new FileReader(words);

			bf = new BufferedReader(fr);
			String line;
			while ((line = bf.readLine()) != null) {
				wordList.add(line.toLowerCase());
				wordSet.add(line.toLowerCase());
			}
			size = wordList.size();
			System.out.println(size + "-" + wordList.get(0)+ "-" + wordList.get(size-1));
			while (true) {
				System.out.println();
				System.out.println("Enter a phrase or Q to quit");
				String input = in.nextLine().toLowerCase();
				if (input.equals("Q")) {
					System.out.println("bye");
					break;
				}
				String inp[] = input.split(" ");
				// List search
				long startTime = System.currentTimeMillis();
				for (String word : inp) {
					if (!search(word, 0, size-1)) {
						System.out.println(word + " is not a word. Please reenter.");
						break;
					}
				}
				long endTime = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				System.out.println("time with list-" + totalTime);
				
				System.out.println();
				
				//list contains search
				startTime = System.currentTimeMillis();
				for (String word : inp) {
					if (!wordList.contains("word")) {
						System.out.println(word + " is not a word. Please reenter.");
						break;
					}
				}
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				System.out.println("time with list contains- " + totalTime);
				
				//Set search
				startTime = System.currentTimeMillis();
				for (String word : inp) {
					if (!wordSet.contains("word")) {
						System.out.println(word + " is not a word. Please reenter.");
						break;
					}
				}
				endTime = System.currentTimeMillis();
				totalTime = endTime - startTime;
				System.out.println("time with set- " + totalTime);
				
				
			}
			
			

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			in.close();
			try {
				bf.close();
				fr.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}

		}

	}

	private static boolean search(String word, int min, int max) {
		
		boolean result = false;
		while (min >= 0 && max <size) {
			int mid = (min + max) / 2;
			//System.out.println(wordList.get(mid));
			if (word.equalsIgnoreCase(wordList.get(mid))) {
				
				System.out.println(word + "-" + mid);
				result = true;
				break;
			} else if (word.compareTo(wordList.get((min + max) / 2)) > 0) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}
		
		return result;
	}

}
