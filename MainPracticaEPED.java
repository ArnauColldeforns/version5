 

public class MainPracticaEPED {
	public static void main(String [] args) throws Exception{
		String scenario = args[0];
		String fileData = args[1];
		String fileOperations=args[2];
		//lectura del fichero de operaciones según el escenario
		AcademiaIF academia = IO.readDataFile(fileData,scenario);
		//salida por consola de las operaciones contenidas en el 
		//fichero de operaciones
		IO.applyOperations(fileOperations,scenario,academia);
	}	
}