package bibbibbub.bibbibbub;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.opencsv.CSVReader;

public class VenueList {
   protected ArrayList<Venue> venueList = new ArrayList();

   public ArrayList<Venue> getVenueList() {
	  return venueList;
   }

   public void setVenueList(ArrayList<Venue> venueList) {
	  this.venueList = venueList;
   }

   public int getLength() {
	  return this.venueList.size();
   }

   public Venue getEntry(int index) {
	  return this.venueList.get(index);
   }

   public VenueList(String inputFile) throws NumberFormatException, IOException {
	  CSVReader reader = null;

	  reader = new CSVReader(new FileReader(inputFile));
	  String[] line;
	  int count = 0;
	  while ((line = reader.readNext()) != null) {
		 if (count != 0) {
			StringBuilder builder = new StringBuilder();
			for (int i = 1; i < line.length - 1; ++i) {
			   builder.append(line[i]);
			   if (i != line.length - 2)
				  builder.append(',');
			}
			venueList.add(new Venue(Integer.parseInt(line[0]), builder
				  .toString(), line[line.length - 1]));
		 }
		 ++count;
	  }
	  System.out.println("Load VenueDB: " + inputFile + " for " + (count - 1)
			+ " Venue Entries");

	  reader.close();

   }
}
