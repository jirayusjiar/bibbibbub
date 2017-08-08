package bibbibbub.bibbibbub;

public class Venue {

   public int getType() {
	  return type;
   }

   public void setType(int type) {
	  this.type = type;
   }

   public String getFullName() {
	  return fullName;
   }

   public void setFullName(String fullName) {
	  this.fullName = fullName;
   }

   public String getAbbrv() {
	  return abbrv;
   }

   public void setAbbrv(String abbrv) {
	  this.abbrv = abbrv;
   }

   protected int type;
   protected String fullName;
   protected String abbrv;

   public Venue(int inType, String inFullName, String inAbbrv) {

	  this.type = inType;
	  this.fullName = inFullName;
	  this.abbrv = inAbbrv;

   }

}
