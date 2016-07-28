
public class AcademiaS implements AcademiaIF {

	
	//es la lista de academias hijas
	private ListIF<AcademiaS> AcademiasHijas;
	
	//es la raiz de mi arbol de academias
	private AcademiaIF raiz;
	
	//es el doctor de la academiaIF
	private DoctorIF doctorAcademia; 
	
	//es la cola de doctores superiores
	private QueueIF<DoctorIF> colaDoctorSuperior;
	
	//getters and setters
	
	public ListIF<AcademiaS> getAcademiasHijas() {
		return AcademiasHijas;
	}



	public void setAcademiasHijas(ListIF<AcademiaS> academiasHijas) {
		AcademiasHijas = academiasHijas;
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



	public QueueIF<DoctorIF> getColaSuperior() {
		return colaDoctorSuperior;
	}



	public void setDoctorAcademiaSuperior(QueueIF<DoctorIF> DoctoresSuperior) {
		this.colaDoctorSuperior=DoctoresSuperior;
	}



	//constructor de academiaS
	public AcademiaS() {}
				

	
	@Override
	public boolean isEmpty() {
		// si no tiene doctor fundador devolvemos falso, eso quiere decir que la lista de academias hijas de raiz esta vacia
		if(((AcademiaS) this.getRaiz()).getAcademiasHijas().isEmpty()) return true;
		//si no tenemos ningun estudiante tambien podemos decir que esta vacia
		else if(((AcademiaS) this.getRaiz()).getAcademiasHijas().get(1).getAcademiasHijas().isEmpty()) return true;
		else return false;
		
	}
 
	@Override
	/**
	 * Este método me devuelve true si existe el doctor en mi academia
	 */
	public boolean contains(DoctorIF e) {
		
		//creamos un iterador desde raiz
		
		
		IteratorIF<DoctorIF> itdoctor=this.iterator();
		while(itdoctor.hasNext()){
			if(itdoctor.getNext().getId()==e.getId()) return true;
		}
		
		//llegado a este punto es que no tenemos el doctor
		return false;
		
	}

	

	@Override
	public void clear() {
		// Borramos la lista de hijos de raiz
		((AcademiaS) this.getRaiz()).getAcademiasHijas().clear();
	}

	@Override
	public IteratorIF<DoctorIF> iterator() {
		
		ListIF<DoctorIF> ListaDoctores=iteratorR(((AcademiaS) this.getRaiz()).getAcademiasHijas(),new List<DoctorIF>());
		
		return ListaDoctores.iterator();
		
	}

	private ListIF<DoctorIF> iteratorR(ListIF<AcademiaS> academiasHijas2, ListIF<DoctorIF> listaDoctores) {
		// metodo trivial, si la lista de academias esta vacia entonces devolvemos el resultado final
		if(academiasHijas2.isEmpty()) return listaDoctores;
		
		else{
			ListIF<AcademiaS> nuevaListahijos=new List<AcademiaS>();
			
			//iteramos la lista de hijos
			
			IteratorIF<AcademiaS> ithijos=academiasHijas2.iterator();
			
			while(ithijos.hasNext()){
				AcademiaS next=ithijos.getNext();
				//guardamos el doctor de next
				int positionlistaDoctores=listaDoctores.size()+1;
				listaDoctores.insert(next.getDoctorAcademia(), positionlistaDoctores);
				//buscamos las academis hijas de next
				
				ListIF<AcademiaS> hijasNext=next.getAcademiasHijas();
				//iteramos las hijas de next
				IteratorIF<AcademiaS> itnexthijas=hijasNext.iterator();
				while(itnexthijas.hasNext()){
					int tamanohijasnext=nuevaListahijos.size()+1;
					nuevaListahijos.insert(itnexthijas.getNext(), tamanohijasnext);
				}
			}
			
			return iteratorR(nuevaListahijos,listaDoctores);
		}
	}



	@Override
	public DoctorIF getFounder() {
		
		return ((AcademiaS) this.getRaiz()).getAcademiasHijas().get(1).getDoctorAcademia();
	}

	@Override
	public DoctorIF getDoctor(int id) {
		//creamos un iterador desde raiz
		IteratorIF<DoctorIF> itdoctor=this.iterator();
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
				IteratorIF<DoctorIF> itdoctor=this.iterator();
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
			AcademiaS academiaDoctorSuperior=((DoctorS) supervisor).getAcademy();
			AcademiaS academiaNuevoDoctor=((DoctorS) newDoctor).getAcademy();
			
			// tenemos que insertar la academia nuevo doctor en la lista de academias hijas del supervisor
			
			int sizeListaHijosSupervisor=academiaDoctorSuperior.getAcademiasHijas().size()+1;
			
			academiaDoctorSuperior.getAcademiasHijas().insert(academiaNuevoDoctor, sizeListaHijosSupervisor);
			
		}
		
	}
	
	@Override
	public void addSupervision(DoctorIF student, DoctorIF supervisor) {
		
		
	}
	/**
	 * Este método solamente se aplica una vez al crear la academia.
	 * Lo que hace es anadir un nuevo nodo academia justo despues de
	 * la raiz
	 * @param founder
	 */
	public void setFounder(DoctorS founder) {
		//creamos una academia raiz
		AcademiaS nuevaraiz=new AcademiaS();
		//hacemos los set
		nuevaraiz.setAcademiasHijas(new List<AcademiaS>());
		this.setRaiz(nuevaraiz);
		//inicializamos los setters de lo que va ser la academia del fundador
		this.setDoctorAcademia(founder); //el doctor es el doctor fundador
		this.setDoctorAcademiaSuperior(null); //el doctor superior es nullo
		this.setAcademiasHijas(new List<AcademiaS>());
		nuevaraiz.getAcademiasHijas().insert(this, 1);	
		
	}
	
	
		
}

