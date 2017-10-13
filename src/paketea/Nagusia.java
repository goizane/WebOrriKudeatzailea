package paketea;

import java.io.IOException;

public class Nagusia {
	
	// Metodo nagusien probak egiteko programa
	
	public static void main(String[] args) throws IOException {
		
		WebOrriLista webOrriZerrenda = WebOrriLista.getWebOrriLista();
		GakoHitzLista gakoHitzZerrenda = GakoHitzLista.getGakoHitzLista();
		
		
		System.out.println("*****FITXATEGI TXIKIEKIN PROBAK*****");
		
		// 1. ERAGIKETA: Datuak kargatu fitxategietatik
		System.out.println("1) DATUAK KARGATU FITXATEGIETATIK");
		// 1.1 Web-orriak kargatu URL-en fitxategitik
		webOrriZerrenda.kargatuURL("src/fitxategiak/smallindex.txt");
		System.out.println("--> Web-orriak kargatuta!!!");
		// 1.2 Web-orri bakoitzaren estekak kargatu esteken fitxategitik
		webOrriZerrenda.kargatuEstekak("src/fitxategiak/smallpld-arc.txt");
		System.out.println("--> Estekak kargatuta!!!");
		// 1.3 Web-orri bakoitzaren gako-hitzak kargatu hiztegiaren fitxategitik
		gakoHitzZerrenda.kargatuHitzak("src/fitxategiak/words.txt");
		System.out.println("--> Gako hitzak kargatuta!!!");


		// 2. ERAGIKETA: Web-orri baten bilaketa
		System.out.println("");
		System.out.println("2) WEB ORRI BATEN BILAKETA");
		// 2.1 Web-orri bat zerrendan bilatu, bere URL-a emanda
		WebOrri webOrriBat = webOrriZerrenda.webOrriaBilatuURL("01webmaster.com");
		System.out.println("--> Web-orria aurkitu du URL honekin: " + webOrriBat.getUrl());	
		webOrriBat = webOrriZerrenda.webOrriaBilatuURL("kaixo.com");
		if (webOrriBat == null){
			System.out.println("--> Ez du web-orria aurkitu du URL honekin: kaixo.com ");	
		}

		// 2.2 Web-orri bat zerrendan bilatu, bere INDIZEA emanda
		WebOrri webOrriBi = webOrriZerrenda.webOrriaBilatuINDIZEA(271);
		System.out.println("--> Web-orria aurkitu du INDIZE honekin: " + webOrriBi.getIndizea());
		webOrriBi = webOrriZerrenda.webOrriaBilatuINDIZEA(-3);
		if (webOrriBi == null){
			System.out.println("--> Ez du web-orria aurkitu du INDIZE honekin: -3 ");	
		}


		// 3. ERAGIKETA: Web-orri berri baten txertaketa
		System.out.println("");
		System.out.println("3) WEB-ORRI BERRI BATEN TXERTAKETA");
		String webOrriURL = "egela1718.ehu.eus";
		webOrriZerrenda.webOrriBerriaTxertatu(webOrriURL);
		System.out.println("--> Web-orri berri hau txertatu da: " + webOrriURL);
		webOrriURL = "01webmaster.com";
		webOrriZerrenda.webOrriBerriaTxertatu(webOrriURL);	//txertatuta dago jada

		// 4. ERAGIKETA: Web-orri batek estekatzen dituen web-orrien zerrenda bueltatu
		System.out.println("");
		System.out.println("4) WEB-ORRI BATEK ESTEKATZEN DITUEN WEB-ORRIEN ZERRENDA BUELTATU");
		WebOrri webOrriHiru = new WebOrri("01webmaster.com", 271);
		System.out.println("--> Web-orri honek estekatzen dituen web-orrien zerrenda inprimatuko da: " + webOrriHiru.getUrl());
		webOrriZerrenda.inprimatuEstekak(webOrriHiru.getIndizea());

		// 5. ERAGIKETA: Web-orrien zerrenda eta hauen esteken zerrenda fitxategitan gorde
		System.out.println("");
		System.out.println("5) WEB-ORRIEN ZERRENDA FITXATEGI BERRI BATEAN GORDE");
		webOrriZerrenda.webOrriListaGorde();
		System.out.println("--> Fitxategi (txt) berri bat sortu da eta web-orri zerrenda bertan gorde da!");
		System.out.println("5) WEB-ORRIEN ESTEKEN ZERRENDA FITXATEGI BERRI BATEAN GORDE");
		webOrriZerrenda.estekaListaGorde();
		System.out.println("--> Fitxategi (txt) berri bat sortu da eta webOrri-en esteken zerrenda bertan gorde da!");
		
		// 6. ERAGIKETA: Web-orrien zerrenda ordenatua lortu alfabetikoki (QuickSort)
		System.out.println("");
		System.out.println("6) WEB-ORRIEN ZERRENDA ORDENATUA LORTU");
		webOrriZerrenda.webOrriListaOrdenatu(webOrriZerrenda);
		System.out.println("--> Web-orrien zerrenda ordenatu da!");



		System.out.println("");
		System.out.println("*************************FITXATEGI HANDIEKIN PROBAK*************************");
		/*
		// 1. ERAGIKETA: Datuak kargatu fitxategietatik
		System.out.println("1) DATUAK KARGATU FITXATEGIETATIK");
		// 1.1 Web-orriak kargatu URL-en fitxategitik
		webOrriZerrenda.kargatuURL("src/fitxategiak/index.txt");
		System.out.println("--> Web-orriak kargatuta!!!");
		// 1.2 Web-orri bakoitzaren estekak kargatu esteken fitxategitik
		webOrriZerrenda.kargatuEstekak("src/fitxategiak/pld-arc.txt");
		System.out.println("--> Estekak kargatuta!!!");
		// 1.3 Web-orri bakoitzaren gako-hitzak kargatu hiztegiaren fitxategitik
		gakoHitzZerrenda.kargatuHitzak("src/fitxategiak/words.txt");
		System.out.println("--> Gako hitzak kargatuta!!!");
		*/
	}
}