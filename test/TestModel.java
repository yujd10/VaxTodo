/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Model.*;

/**
 * @author yaovi gadedjro
 *
 */
class TestModel {
	
	
	@Before
	void setUp(){
	}
	

	@Test
	void visitConstruct() {

		Visit viz= new Visit(true,"kayne","w","2","2022-12-04", "15h");;
		assertEquals("kayne",viz.getFirstName());
		//assertEquals(false,time.isDateValid(time.getDate()));	
	}
	
	@Test
	void dateFormat() {
	
		DateTime time = new DateTime("2022-12-04","15h00"); 
		assertEquals(false,DateTime.isDateValid("2022-12-04"));
		assertTrue(time.getTime()=="15h00");
		
	}
	
	@Test
	/**
	 * Test functions in class VaccineProfile
	 * And some basic functionalities in class "Vaccine"
	 */
	void vaccination() {
		
		DateTime heure = new DateTime("2022-12-04","15h00");
		Vaccine vax = new Vaccine("West-Vaxine","K-W2021","00001");
		VaccineProfile vp= new VaccineProfile("23",heure,vax);
		
		
	}
	

	
	
}
