package	mytests;

import org.junit.*;


import es.deusto.spq.donaciones.dao.Donativo;
import es.deusto.spq.donaciones.dao.GestorDonativosBD;
import es.deusto.spq.util.dao.DataBaseManager;

/**
 * @author      Diego L�pez-de-Ipi�a <dipina@deusto.es>
 * @version     1.0                                    
 * @since       2013-03-10          
 * <p><This program tests a DAO object that gives access to a Donations DB through <b>JDBC/b></p> 
 *
 */

public class DonacionesGestorBDTest {
	/**
	 * This variable represents the persitency manager factory instance
	 */
	private GestorDonativosBD gestorBD;
	
	
	/**
	 * It initializes the variables used by the other methods
	 */
	@Before
    public void setUp() throws Exception {
        // Code executed before each test    
		this.gestorBD = new GestorDonativosBD(DataBaseManager.DBType.SQLITE, "db/donaciones.db");        
    } 
	
	/**
	 * converts to upper cases. Simple (bogus) method only created to show how to document the input paramters and output of a method.
	 * @param str String to convert to capital letters
	 * @return The string converted into capital letters 
	*/
	private String convert2Upcase(String str) {
		return str.toUpperCase();
	}
	
	
	/**
	 * tests Product creation
	*/
	@Test
    public void testDonationCreation() {
        try
        {
            System.out.println(this.convert2Upcase("Persisting a donation"));
			this.gestorBD.almacenarDonativo(1000, this.gestorBD.obtenerTotalAcumulado());
			Donativo donativo = this.gestorBD.obtenerUltimoDonativo();
            System.out.println("testDonationCreation: " + donativo.toString());
        }
        finally {
        }
	}

	/**
	 * tests Product Extents
	*/
	@Test
    public void testListingDonativos() {
        try {
			System.out.println("\ntestListingDonativos:");
            for (Donativo donativo: this.gestorBD.getListaDonativos()) {
				System.out.println(donativo.toString());
            }
        }
        catch (Exception e) {
            System.out.println("Exception thrown during retrieval of Extent : " + e.getMessage());
        }
        finally {
        }
	}

	/**
	 * removes everything not needed after executing a test
	*/
	@After
    public void tearDown() throws Exception {
		
        if (this.gestorBD != null) {
			this.gestorBD.finalizar();
		}
		
    }
}
