package bibbibbub.bibbibbub;

import junit.framework.TestCase;

public class BibListTest extends TestCase {

   protected void setUp() throws Exception {
	  super.setUp();
   }

   protected void tearDown() throws Exception {
	  super.tearDown();
   }

   /**
    * Rigourous Test :-)
    */
   public void testApp() {
	  System.out.println("Working Directory = "
			+ System.getProperty("user.dir"));
	  System.out.println("Start testing BibList");
	  BibList test = new BibList("./src/res/myref.bib");

	  assertTrue(true);
   }

}
