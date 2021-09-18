package text_analyzer;
import java.io.*;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.net.URL;
import java.util.Collections;

public class Text_analyzer
{
  public static void main(String[] args) throws Exception
  {
 	URL url = new URL("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm"); //Create object for URL.
	BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream())); //Open a reader to stream the file
	BufferedWriter writer = new BufferedWriter(new FileWriter("Test_Download.txt")); //Create file to store the downloaded info.
	
	String line;
            while ((line = readr.readLine()) != null) //Read the html source code from the website.
             {
                writer.write(line); //Write the html code to a local file.
			 }
			readr.close();
            writer.close();


        File file = new File("Test_Download.txt"); //Open the file we have written for a word count analysis.

        Scanner sc = new Scanner(file); //Create a scanner to read the open text file.
        int i = 0, indexOfWord = 0, count = 0;
        List<String> words = new ArrayList<String>(); //Create two arrays to store the words and word count.
        List<Integer> wordCount = new ArrayList<Integer>();

       while (sc.hasNext()) //Starting here we read each individual word of the file.
       {
        String word = sc.next();
        word = word.replaceAll("\\p{Punct}", " "); //This removes the puncuation and any non letters.
         
        if(word.equals("Once"))  // once the scanner reaches the first word of the actual poem we start a new method.
         {						 //this new method stores each new word and increments the counter for repeated words.
          while(!word.equals("END"))//we stay in this method until the last word of the poem is reached.
          {
           if(words.contains(word))//Here if the word already exist we just increment a counter for that word.
           {
            indexOfWord = words.indexOf(word);
            count = wordCount.get(indexOfWord);
            count = count+1;
            wordCount.add(indexOfWord, count);        
           }
           else
           {
            word = word.replaceAll("\\s.*", "");//If the word does not already exist in the array we add it and start a counter for it.
            words.add(i,word);
            wordCount.add(i,1);
            i++;
           }
           word = sc.next();
           word = word.replaceAll("\\p{Punct}", " ");
          }
        }
      }   	      
        sc.close();
        
  
              Integer max = Collections.max(wordCount); //At this point we go through the array and sort it by max occurance of words
              System.out.println("max word occurence is:" + max);// and then display them in descending order.
              System.out.println("|Count|Word");
        	  System.out.println("|-----|----");
              
        int no_of_elements = words.size();
        
        while(max != 0)
        {
         for(int j = 0; j < no_of_elements; j++)
         {       	       	
        	 String word = words.get(j);
        	 count = wordCount.get(j);
        	 if(count == max)
        	 {
        	 if(word.isEmpty()){continue;}// I put this in because the program was displaying random blanks for some reason.
        	 System.out.printf("|%-4d", count);
        	 System.out.printf(" |" + word + "\n");
        	 }
		
         }
         max--;
        }
    }
 }

