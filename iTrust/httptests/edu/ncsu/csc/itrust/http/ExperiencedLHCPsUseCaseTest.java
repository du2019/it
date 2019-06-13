package edu.ncsu.csc.itrust.http;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

/**
 * Use Case 29
 */
public class ExperiencedLHCPsUseCaseTest extends iTrustHTTPTest {
	
	protected void setUp() throws Exception{
		super.setUp();
		gen.clearAllTables();
		gen.standardData();
		gen.patient_hcp_vists();
		gen.hcp_diagnosis_data();		
	}

	public void testViewDiagnoses() throws Exception {		
		WebConversation wc = login("1", "pw");
		WebResponse wr = wc.getCurrentPage();
		assertEquals("iTrust - Patient Home", wr.getTitle());
		wr = wr.getLinkWith("My Diagnoses").click();
		assertEquals("iTrust - My Diagnoses", wr.getTitle());
		
		assertTrue(wr.getText().contains("Echovirus(79.10)"));
		assertTrue(wr.getText().contains("Acute Lycanthropy(250.00)"));
	}
	
	public void testViewDiagnosisEchoVirus() throws Exception {
		WebConversation wc = login("1", "pw");
		WebResponse wr = wc.getCurrentPage();
		assertEquals("iTrust - Patient Home", wr.getTitle());
		wr = wr.getLinkWith("My Diagnoses").click();
		assertEquals("iTrust - My Diagnoses", wr.getTitle());
		
		wr = wr.getLinkWith("Echovirus(79.10)").click();
		wr = wr.getLinkWith("Transaction Log").click();
		assertTrue(wr.getText().contains("Find LHCPs with experience with a diagnosis"));	
	}
	
	public void testViewHCPDetails() throws Exception {
		WebConversation wc = login("1", "pw");
		WebResponse wr = wc.getCurrentPage();
		assertEquals("iTrust - Patient Home", wr.getTitle());
		wr = wr.getLinkWith("My Diagnoses").click();
		assertEquals("iTrust - My Diagnoses", wr.getTitle());
		
		wr = wr.getLinkWith("Echovirus(79.10)").click();
		wr = wr.getLinkWith("Jason Frankenstein").click();

		assertEquals("iTrust - View Personnel Details", wr.getTitle());
		assertTrue(wr.getText().contains("Jason Frankenstein"));		
	}
}