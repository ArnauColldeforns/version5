
public class AcademiaC implements AcademiaIF {

	
	//es la lista de academias supervisoras
	private ListIF<AcademiaC> AcademiasSupervisor;
	
	
	//es la raiz de mi arbol de academias
	private AcademiaIF raiz;
	
	//es el doctor de la academiaIF
	private DoctorIF doctorAcademia; 
	
	private ListIF<DoctorIF> ListaDoctoresSuperiores;
	
	//getters and setters
	
	public ListIF<AcademiaC> getAcademiasSupervisoras() {
		return AcademiasSupervisor;
	}



	public void setAcademiasSupervisoras(ListIF<AcademiaC> academiasSup) {
		this.AcademiasSupervisor = academiasSup;
	}



	public AcademiaIF getRaiz() {
		return raiz;
	}



	public void setRaiz(AcademiaIF raiz) {
		this.raiz = raiz;
	}



	public DoctorIF getDoctorAcademia() {
		return doctorAcademia;
	}



	public void setDoctorAcademia(DoctorIF doctor) {
		this.doctorAcademia = doctor;
	}
	
	public ListIF<DoctorIF> getListaDoctoresSuperiores() {
		return ListaDoctoresSuperiores;
	}



	public void setListaDoctoresSuperiores(ListIF<DoctorIF> listaDoctoresSuperiores) {
		ListaDoctoresSuperiores = listaDoctoresSuperiores;
	}
	//constructor de academiaS
	public AcademiaC() {}
				

	
	@Override
	public boolean isEmpty() {
		// si no tiene doctor fundador devolvemos falso, eso quiere decir que la lista de academias hijas de raiz esta vacia
		if(((DoctorC) this.getFounder()).getStudents().isEmpty())return true;
		else return false;
		
	}
 
	@Override
	/**
	 * Este método me devuelve true si existe el doctor en mi academia
	 */
	public boolean contains(DoctorIF e) {
		
		//creamos un iterador desde raiz
		
		
		IteratorIF<DoctorIF> itdoctor=this.getRaiz().iterator();
		while(itdoctor.hasNext()){
			
			if(itdoctor.getNext().getId()==e.getId()) return true;
		}
		
		//llegado a este punto es que no tenemos el doctor
		return false;
		
	}

	

	@Override
	public void clear() {
		
		//creamos un iterador desde raiz
		
		
		IteratorIF<DoctorIF> itdoctor=this.iterator();
		
		while(itdoctor.hasNext()){
			//borramos las academias
		((DoctorC) itdoctor.getNext()).setAcademia(null);
		
		}
		//borramos los estudiantes del fundador
		this.getFounder().getStudents().clear();
		
	}

	@Override
	public IteratorIF<DoctorIF> iterator() {
		QueueIF<DoctorIF> colaDoctores=new Queue<DoctorIF>();
		//insertamos el fundador en primer lugar
		if(this.getDoctorAcademia()!=null){
		colaDoctores.enqueue(this.getDoctorAcademia());}
		QueueIF<DoctorIF> nuevaColaDoctores=iteratorR(colaDoctores,new Queue<DoctorIF>());
		return nuevaColaDoctores.iterator();
		
	}

	private QueueIF<DoctorIF> iteratorR(QueueIF<DoctorIF> colaDoctores, Queue<DoctorIF> queueResultado) {
		// metodo trivial, si la lista de academias esta vacia entonces devolvemos el resultado final
		if(colaDoctores.isEmpty()) return queueResultado;
		
		else{
			//creamos una nueva cola de doctores para el caso recursivo
			QueueIF<DoctorIF> nuevaColaDoctores=new Queue<DoctorIF>();
			//guardamos la cola de doctores a la cola resultado
			
			IteratorIF<DoctorIF> ithijos=colaDoctores.iterator();
			
			while(ithijos.hasNext()){
				DoctorIF next=ithijos.getNext();
				//guardamos el doctor next en la cola de resultados
				queueResultado.enqueue(next);
				//cogemos los estudiantes del doctor next
				if(next.getStudents()!=null){
				IteratorIF<DoctorIF> itestudiantes=next.getStudents().iterator();
				while(itestudiantes.hasNext()){
					DoctorIF hijo=itestudiantes.getNext();
					if(!((DoctorC) hijo).getAcademy().getAcademiasSupervisoras().isEmpty()){
					if(((DoctorC) hijo).getAcademy().getAcademiasSupervisoras().get(1).getDoctorAcademia().equals(next)){
					nuevaColaDoctores.enqueue(hijo);
					}
					}
				}
				}
					
			}
				
			
			
			return iteratorR(nuevaColaDoctores,queueResultado);
		
	}
		
	}


	@Override
	public DoctorIF getFounder() {
		
		return ((AcademiaC) this.getRaiz()).getDoctorAcademia();
	}

	@Override
	public DoctorIF getDoctor(int id) {
		//creamos un iterador desde raiz
		IteratorIF<DoctorIF> itdoctor=this.getRaiz().iterator();
				while(itdoctor.hasNext()){
					DoctorIF doctornext=itdoctor.getNext();
					if(doctornext.getId()==id) return doctornext;
				}
				
				//llegado a este punto es que no tenemos el doctor
				return null;
	
		
	}



	@Override
	public int size() {
		int contador = 0;
		//creamos un iterador desde raiz
				IteratorIF<DoctorIF> itdoctor=this.getRaiz().iterator();
						while(itdoctor.hasNext()){
							itdoctor.getNext();
							contador++;
						}
						
						//llegado a este punto es que no tenemos el doctor
						return contador;
				}

	@Override
	public void addDoctor(DoctorIF newDoctor, DoctorIF supervisor) {
		
		if(this.contains(supervisor)&&!this.contains(newDoctor)){
			
			//Primero encontramos las academias de supervisor y de nuevo doctor
			
				AcademiaC academiaDoctorSuperior=((DoctorC) supervisor).getAcademy();
				
			AcademiaC academiaNuevoDoctor=((DoctorC) newDoctor).getAcademy();
		
			// tenemos que apuntar la academia del supervisor con la lista de academias del nuevo doctor
			academiaNuevoDoctor.getAcademiasSupervisoras().insert(academiaDoctorSuperior, 1);
			
		}
		
	}
	
	@Override
	public void addSupervision(DoctorIF student, DoctorIF supervisor) {
		if(this.contains(student)&&this.contains(supervisor)){
			//Primero encontramos las academias de supervisor y de nuevo doctor
			
			AcademiaC academiaDoctorSuperior=((DoctorC) supervisor).getAcademy();
				
			AcademiaC academiaEstudianteDoctor=((DoctorC) student).getAcademy();
			// tenemos que apuntar la academia del supervisor con la lista de academias del nuevo doctor
			int Nuevosize=academiaEstudianteDoctor.getAcademiasSupervisoras().size()+1;
			academiaEstudianteDoctor.getAcademiasSupervisoras().insert(academiaDoctorSuperior, Nuevosize);
			
			
		}
		
	}
	/**
	 * Este método solamente se aplica una vez al crear la academia.
	 * Lo que hace es anadir un nuevo nodo academia justo despues de
	 * la raiz
	 * @param founder
	 */
	public void setFounder(DoctorC founder) {
		this.setDoctorAcademia(founder);
		this.setRaiz(this);
		
		
		
		
	}
	
	
		
}

