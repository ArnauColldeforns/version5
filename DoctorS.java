public class DoctorS implements DoctorIF {
private int id; /* Identificador univoco del Doctor */
private AcademiaS academia; /* Academia a la que pertenece el Doctor */

//este constructor me sirve para crear un nuevo doctor cuando estamos leiendo el archivo de texto
public DoctorS(int iD2, AcademiaIF academiaRaiz, DoctorIF doctorSuperior) {
	//creamos una nueva sub academia
	AcademiaS nueva=new AcademiaS();
	
	
	//inicializamos los setters de esa nueva academia
	
	nueva.setRaiz(academiaRaiz); //siempre guardamos la raiz del arbol 
	
	QueueIF<DoctorIF> nuevaColaDoctorSuperior=new Queue<DoctorIF>();
	
	nuevaColaDoctorSuperior.enqueue(doctorSuperior);
	
	nueva.setDoctorAcademiaSuperior(nuevaColaDoctorSuperior); //guardamos la cola de doctores superiores
	
	if(((DoctorS) doctorSuperior).getAcademy().getColaSuperior()!=null){
		
		IteratorIF<DoctorIF> iterador=((DoctorS) doctorSuperior).getAcademy().getColaSuperior().iterator();
		
		
		
		while(iterador.hasNext())nuevaColaDoctorSuperior.enqueue(iterador.getNext());}
	
	nueva.setAcademiasHijas(new List<AcademiaS>());  //inicialmente no tenemos ninguna academia hija
	
	nueva.setDoctorAcademia(this);
	
	this.setAcademia(nueva);
	
	
	this.id=iD2; //definimos el valor de id en el constructor de nuestro nuevo doctor
	
	}
	
//es el constructor, vamos utilizar los setters para ir inicianlizando todos los valores
public DoctorS() {
	// TODO Auto-generated constructor stub
}
//este constructor me sirve para crear el fundador. 
public DoctorS(int iD2) {
	
		this.id=iD2;
}

public AcademiaS getAcademy(){
	return academia;
}
/* Consulta el director de Tesis del doctor */
/* @returns el Doctor que fue su director de Tesis */
/* null en caso de que sea el fundador de la Academia */
public DoctorIF getSupervisor(){
	
	AcademiaS Academiadoctor=this.getAcademy();
	
	return Academiadoctor.getColaSuperior().getFirst();
	
	}


@Override
public CollectionIF<DoctorIF> getAncestors(int generations) {
	//creamos una pila de ancestors
		StackIF<DoctorIF> Pilaancestors=new Stack<DoctorIF>();
		AcademiaS Academiadoctor=this.getAcademy(); 
		IteratorIF<DoctorIF> itdoctorsuperior=Academiadoctor.getColaSuperior().iterator();
		while(itdoctorsuperior.hasNext()&&generations>0){
			Pilaancestors.push(itdoctorsuperior.getNext());
			generations--;
		}
		
		//devolvemos el resultado
		return Pilaancestors;
	}

@Override
public CollectionIF<DoctorIF> getStudents() {
	
	QueueIF<DoctorIF> colaEstudiantes=new  Queue<DoctorIF>();
	
	AcademiaS Academiadoctor=this.getAcademy(); 
	
	ListIF<AcademiaS> listaAcademiasDoctor=Academiadoctor.getAcademiasHijas();
	
	IteratorIF<AcademiaS> itlista=listaAcademiasDoctor.iterator();
	
	while(itlista.hasNext()) colaEstudiantes.enqueue(itlista.getNext().getDoctorAcademia());
		
		return colaEstudiantes;
}

public CollectionIF<DoctorIF> getDescendants(int generations) {
	StackIF<DoctorIF> doctoresDescendenties=getDescendantsR(this.getAcademy().getAcademiasHijas(),new Stack<DoctorIF>(),generations);
	return doctoresDescendenties;
}


private StackIF<DoctorIF> getDescendantsR(ListIF<AcademiaS> academiasHijas, Stack<DoctorIF> stackDoctores, int generations) {
	// caso trivial
	if(academiasHijas.isEmpty()||generations==0) return stackDoctores;
	else{
		
		//creamos una nueva lista de academias Hijas
		ListIF<AcademiaS> nuevaAcademiaHijas=new List<AcademiaS>();
		//iteramos los hijos
		IteratorIF<AcademiaS> itacademia=academiasHijas.iterator();
		while(itacademia.hasNext()){
			AcademiaS AcademiaHija=itacademia.getNext();
			//guardamos el doctor en la pila
			stackDoctores.push(AcademiaHija.getDoctorAcademia());
			//buscamos los hijos de es academia hija
			ListIF<AcademiaS> getListaHijos=AcademiaHija.getAcademiasHijas();
			//iteramos esa lista para guardarla en la nueva lista
			IteratorIF<AcademiaS> itgetListaHijos=getListaHijos.iterator();
			while(itgetListaHijos.hasNext()){
				nuevaAcademiaHijas.insert(itgetListaHijos.getNext(), 1);
			}
		}
	
		generations--;//actualizamos el valor de generations
		return getDescendantsR(nuevaAcademiaHijas, stackDoctores, generations);
	
		
	}
	
}

@Override
public CollectionIF<DoctorIF> getSiblings() {
	
	DoctorS superior=(DoctorS) ((AcademiaS) this.getAcademy()).getColaSuperior().getFirst();
	AcademiaS siblings=superior.getAcademy();
	ListIF<AcademiaS> hermanos=siblings.getAcademiasHijas();
	QueueIF<DoctorIF> resultado=new Queue<DoctorIF>();
	for(int i=1;i<=hermanos.size();i++){
		if(!this.equals(hermanos.get(i).getDoctorAcademia()))resultado.enqueue(hermanos.get(i).getDoctorAcademia());
		
	}
	return resultado;
	
	
}
@Override
/**
 * Este m�todo nos sirve para devolver el id del doctor
 */
public int getId() {
	// TODO Auto-generated method stub
	return id;
}

public void setAcademia(AcademiaS a) {
	// TODO Auto-generated method stub
	this.academia=a;
	
}




}