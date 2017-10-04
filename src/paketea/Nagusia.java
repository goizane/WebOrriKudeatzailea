package paketea;

public class Nagusia {
	
	public static void main(String[] args) {
		WebOrriLista lista=null;
		lista = WebOrriLista.getWebOrriLista();
		
		String indexHandia = "/src/fitxategiak/index";
		String estekakHandia = "/src/fitxategiak/pld-arc";
		lista.kargatuURL(indexHandia);
		System.out.println("WEB ORRIAK KARGATUTA!!!");
		lista.kargatuEstekak(estekakHandia);
		System.out.println("ESTEKAK KARGATUTA!!!");
		
		/*
		String indexTxikia = "/src/fitxategiak/smallindex";
		String estekakTxikia = "/src/fitxategiak/smallpld-arc";
		lista.kargatuURL(indexTxikia);
		System.out.println("WEB ORRIAK KARGATUTA!!!");
		lista.kargatuEstekak(estekakTxikia);
		System.out.println("ESTEKAK KARGATUTA!!!");
		*/
		
		lista.inprimatuEstekak(892);
	}
}