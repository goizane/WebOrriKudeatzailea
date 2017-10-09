package paketea;

import java.io.IOException;
import java.util.ArrayList;

public class Nagusia {
	
	// Metodo nagusien probak egiteko programa
	
	public static void main(String[] args) throws IOException {
		
		WebOrriLista webOrriZerrenda = WebOrriLista.getWebOrriLista();
		
		// 1. ERAGIKETA: Datuak kargatu fitxategietatik
		// 1.1 Web-orriak kargatu URL-en fitxategitik
		webOrriZerrenda.kargatuURL("/src/fitxategiak/smallindex.txt");
		System.out.println("WEB ORRIAK KARGATUTA!!!");
		// 1.2 Web-orri bakoitzaren estekak kargatu esteken fitxategitik
		webOrriZerrenda.kargatuEstekak("/src/fitxategiak/smallpld-arc.txt");
		System.out.println("ESTEKAK KARGATUTA!!!");
		// 1.3 Web-orri bakoitzaren gako-hitzak kargatu hiztegiaren fitxategitik
		webOrriZerrenda.gakoHitzakTxertatu("/src/fitxategiak/words.txt");
		System.out.println("GAKO HITZAK KARGATUTA!!!");
		
		// 2. ERAGIKETA: Web-orri baten bilaketa
		// 2.1 Web-orri bat zerrendan bilatu, bere URL-a emanda
		WebOrri webOrriBat = webOrriZerrenda.webOrriaBilatuURL("01webmaster.com");
		System.out.println("Web-orria aurkitu du, URL honekin: " + webOrriBat.getUrl());
		// 2.2 Web-orri bat zerrendan bilatu, bere INDIZEA emanda
		WebOrri webOrriBi = webOrriZerrenda.webOrriaBilatuINDIZEA(271);
		System.out.println("Web-orria aurkitu du, INDIZE honekin: " + webOrriBi.getIndizea());
		// 2.3 Web-orri bat zerrendan bilatu, web-orria osoa emanda. TRUE edo FALSE bueltatu
		WebOrri webOrriHiru = new WebOrri("01webmaster.com", 271);
		boolean badago = webOrriZerrenda.webOrriaBilatu(webOrriHiru);
		if (badago) {
			System.out.println("Web-orri hau zerrendan dago: " + webOrriHiru.getUrl());
		}
		else {
			System.out.println("Web-orri hau ez dago zerrendan: " + webOrriHiru.getUrl());
		}
		
		// 3. ERAGIKETA: Web-orri berri baten txertaketa
		webOrriZerrenda.webOrriBerriaTxertatu("egela1718.ehu.eus");
		
		// 4. ERAGIKETA: Web-orri batek estekatzen dituen web-orrien zerrenda bueltatu
		System.out.println("Web-orri honek estekatzen dituen web-orrien zerrenda inprimatuko da: " + webOrriHiru.getUrl());
		webOrriZerrenda.inprimatuEstekak(webOrriHiru.getIndizea());
		
		// 5. ERAGIKETA: Web-orrien zerrenda fitxategian gorde
		webOrriZerrenda.webOrriListaGorde();
		
		// 6. ERAGIKETA: Web-orrien zerrenda ordenatua lortu (alfabetikoki)
		webOrriZerrenda.webOrriListaOrdenatu(webOrriZerrenda);
		
		
		
		
		// ------- BESTE PROBA BATZUK -------
		
		WebOrriLista lista=null;
		lista = WebOrriLista.getWebOrriLista();
		
		String indexHandia = "/src/fitxategiak/index.txt";
		String estekakHandia = "/src/fitxategiak/pld-arc.txt";
		String words = "/src/fitxategiak/words.txt";
		lista.kargatuURL(indexHandia);
		System.out.println("WEB ORRIAK KARGATUTA!!!");
		lista.kargatuEstekak(estekakHandia);
		System.out.println("ESTEKAK KARGATUTA!!!");
		lista.gakoHitzakTxertatu(words);
		System.out.println("GAKO HITZAK KARGATUTA!!!");
		
		/*
		String indexTxikia = "/src/fitxategiak/smallindex.txt";
		String estekakTxikia = "/src/fitxategiak/smallpld-arc.txt";
		lista.kargatuURL(indexTxikia);
		System.out.println("WEB ORRIAK KARGATUTA!!!");
		lista.kargatuEstekak(estekakTxikia);
		System.out.println("ESTEKAK KARGATUTA!!!");
		*/
		
		lista.inprimatuEstekak(892);
	}
}