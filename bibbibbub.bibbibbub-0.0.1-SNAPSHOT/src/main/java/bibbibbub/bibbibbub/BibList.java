package bibbibbub.bibbibbub;

import info.debatty.java.stringsimilarity.LongestCommonSubsequence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXParser;
import org.jbibtex.Key;
import org.jbibtex.ParseException;
import org.jbibtex.TokenMgrException;

public class BibList {

   private static HashMap<String, Boolean> unCapList = new HashMap<String, Boolean>();
   private static VenueList venueList;

   public static void initList() {
	  String[] tmp = { "the", "an", "a", "for", "aboard", "about", "above",
			"across", "after", "against", "along", "amid", "among", "anti",
			"around", "as", "at", "before", "behind", "below", "beneath",
			"beside", "besides", "between", "beyond", "but", "by",
			"concerning", "considering", "despite", "down", "during", "except",
			"excepting", "excluding", "following", "for", "from", "in",
			"inside", "into", "like", "minus", "near", "of", "off", "on",
			"onto", "opposite", "outside", "over", "past", "per", "plus",
			"regarding", "round", "save", "since", "than", "through", "to",
			"toward", "towards", "under", "underneath", "unlike", "until",
			"up", "upon", "versus", "via", "with", "within", "without", "and",
			"nor", "but", "or", "yet", "so" };
	  for (int i = 0; i < tmp.length; ++i)
		 unCapList.put(tmp[i], true);

   }

   protected String smartCapitalize(String inputText) {
	  String[] splittedText = inputText.split(" ");
	  String output = "";
	  for (int i = 0; i < splittedText.length; ++i) {
		 // If text in unCapList, do nothing, else capitalize
		 if (unCapList.containsKey(splittedText[i])) {
			output += splittedText[i] + " ";
		 } else {
			output += WordUtils.capitalize(splittedText[i]) + " ";
		 }
	  }

	  return (output.substring(0, output.length() - 1));
   }

   protected String getVenue(org.jbibtex.BibTeXEntry inputEntry) {
	  try {
		 return (inputEntry.getField(BibTeXEntry.KEY_JOURNAL).toUserString());
	  } catch (Exception e) {
	  }
	  try {
		 return (inputEntry.getField(BibTeXEntry.KEY_BOOKTITLE).toUserString());
	  } catch (Exception e) {
	  }
	  return ("");
   }

   protected void getMatchedVenue(String inputVenue) {
	  inputVenue = inputVenue.toLowerCase();
	  LongestCommonSubsequence cosine = new LongestCommonSubsequence();
	  int minIndex = 0;
	  double minSimilarity = 10000000;
	  for (int i = 0; i < this.venueList.getLength(); ++i) {
		 Venue tmpV = this.venueList.getEntry(i);
		 double tmpSim = cosine.distance(inputVenue, tmpV.getFullName()
			   .toLowerCase());
		 if (tmpSim < minSimilarity) {
			minIndex = i;
			minSimilarity = tmpSim;
			// System.out.println(inputVenue + "\n" + tmpV.getFullName() + "\n"
			// + tmpSim);
		 } // else if (tmpSim == minSimilarity) {

		 // }

		 System.out.println(inputVenue + "\n" + tmpV.getFullName() + " - "
			   + tmpV.getAbbrv() + "\n" + tmpSim);
	  }
	  System.out.println("MIN SIM = "
			+ this.venueList.getEntry(minIndex).getFullName() + "\n@@@@@");

   }

   public BibList(String inFile) {
	  try {

		 // Load venue list
		 this.venueList = new VenueList("./src/res/se.csv");
		 // Load stopword list
		 initList();

		 Reader reader = null;
		 try {
			reader = new FileReader(inFile);

			BibTeXParser bibtexParser = new BibTeXParser();

			BibTeXDatabase database = bibtexParser.parseFully(reader);
			Map<Key, BibTeXEntry> entryMap = database.getEntries();

			Collection<BibTeXEntry> entries = entryMap.values();
			for (BibTeXEntry entry : entries) {
			   String oldTitle = entry.getField(BibTeXEntry.KEY_TITLE)
					 .toUserString();
			   System.out.print(WordUtils.capitalize(oldTitle) + " (:3) ");
			   System.out.println(this.smartCapitalize(oldTitle));
			   System.out.println(this.getVenue(entry));
			   getMatchedVenue(this.getVenue(entry));
			   //
			   // org.jbibtex.Value value = entry
			   // .getField(org.jbibtex.BibTeXEntry.KEY_TITLE);
			   // entry.removeField(key);
			   // entry.add
			   //
			   // System.out.println(entry.KEY_TITLE.toString());
			   // if (value == null) {
			   // continue;
			   // }

			   // Do something with the title value
			}

			System.out.println("aaa");
			System.out.println("aaa");
			System.out.println("aaa");

		 } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } catch (TokenMgrException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } finally {
			try {
			   reader.close();
			} catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			}
		 }

	  } catch (NumberFormatException e1) {
		 // TODO Auto-generated catch block
		 System.out.println("Unable to download venue list");
		 e1.printStackTrace();
	  } catch (IOException e1) {
		 System.out.println("Unable to download venue list");
		 // TODO Auto-generated catch block
		 e1.printStackTrace();
	  }

   }
}
