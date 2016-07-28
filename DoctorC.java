
public class DoctorC implements DoctorIF{
	private int id; /* Identificador unívoco */
	private AcademiaC academia; /* Academia a la que pertenece */
	private CollectionIF<DoctorIF> students; /* Los estudiantes del doctor */
	
	/**Determina la lista de estudiantes
	 * 
	 * @param Lista Estudiantes
	 */
	public void setEstudiantes(QueueIF<DoctorIF> conjuntoEstudiantes){
		this.students=conjuntoEstudiantes;
		
	}
	
		
	public DoctorC(int iDDoctor, AcademiaIF academiaRaiz, List<DoctorIF> supervisors) {
		
		this.id=iDDoctor;
		
		//creamos una nueva sub academia
		AcademiaC nueva=new AcademiaC();
		
		
		//inicializamos los setters de esa nueva academia
		this.setEstudiantes(new Queue<DoctorIF>());
		
		
		nueva.setRaiz(academiaRaiz); //siempre guardamos la raiz del arbol 
		
		nueva.setDoctorAcademia(this);
		
		nueva.setAcademiasSupervisoras(new List<AcademiaC>());
		
		nueva.setListaDoctoresSuperiores(supervisors);
		//tenemos que guardar en todos los doctores superiores ese doctor como estudiante
		
		//tenemos que anadir ese Doctor en la cola de estudiantes de doctor superior
	
		IteratorIF<DoctorIF> iteradorListadoctoresSuperiores=supervisors.iterator();
		while(iteradorListadoctoresSuperiores.hasNext()){
			DoctorIF doctorSup=iteradorListadoctoresSuperiores.getNext();
			QueueIF<DoctorIF> nuevoColaEstudiantes=(QueueIF<DoctorIF>) ((DoctorC) doctorSup).getStudents();
				
			if(!nuevoColaEstudiantes.contains(this))nuevoColaEstudiantes.enqueue(this);
			((DoctorC) doctorSup).setEstudiantes(nuevoColaEstudiantes);
			
		}
		
		
		
		
		//finalmente guardamos la academia
		this.setAcademia(nueva);
	}

	public DoctorC(int iD) {
		this.id=iD;
		this.setEstudiantes(new Queue<DoctorIF>());
	}

	@Override
	public CollectionIF<DoctorIF> getAncestors(int generations) {
		
		AcademiaC academia=this.getAcademy();
		StackIF<DoctorIF> ancestors=new Stack<DoctorIF>();
		ListIF<AcademiaC> academiaSuperiores=academia.getAcademiasSupervisoras();
		for(int i=1;i<=academiaSuperiores.size();i++){
		ancestors.push(academiaSuperiores.get(i).getDoctorAcademia());}
		
		return getAncestorsR(ancestors,new Stack<DoctorIF>(),generations);
		
	}

	private StackIF<DoctorIF> getAncestorsR(StackIF<DoctorIF> ancestors,Stack<DoctorIF> stackResultado, int i) {
		//caso trivial
		if(ancestors.isEmpty()||i<1) return stackResultado;
		else{
		
			//creamos un nuevo stack
			StackIF<DoctorIF> nuevoStack=new Stack<DoctorIF>();
			IteratorIF<DoctorIF> itStack=ancestors.iterator();
			while(itStack.hasNext()){
				//guardamos el resultado
				DoctorIF superior=itStack.getNext();
				if(!stackResultado.contains(superior))stackResultado.push(superior);
				//buscamos los doctores superiores de superior
				ListIF<AcademiaC> academiasSup=((DoctorC) superior).getAcademy().getAcademiasSupervisoras();
				//iteramos las academiasSuperiores
				if(academiasSup!=null){
				IteratorIF<AcademiaC> itAcademiaSup=academiasSup.iterator();
				while(itAcademiaSup.hasNext()){
					AcademiaC academiaS=itAcademiaSup.getNext();
					nuevoStack.push(academiaS.getDoctorAcademia());
				}
				
				}
				
			}
		i--;	
		return getAncestorsR(nuevoStack, stackResultado, i);
		}
	}


	@Override
	public CollectionIF<DoctorIF> getStudents() {
		// TODO Auto-generated method stub
		return students;
	}

	@Override
	public CollectionIF<DoctorIF> getDescendants(int generations) {
		QueueIF<DoctorIF> colaResultado=new Queue<DoctorIF>();
		// primero creamos un iterador desde doctor
		IteratorIF<DoctorIF> iteradorDescendants=this.getAcademy().getRaiz().iterator();
		iteradorDescendants.getNext(); //pasamos el primer doctor
		while(iteradorDescendants.hasNext()){
			DoctorIF student=iteradorDescendants.getNext();
			if(student.getId()!=this.getId()){
			StackIF<DoctorIF> studentS=new Stack<DoctorIF>();
			studentS.push(student);
			if(getDoctor(studentS,generations))colaResultado.enqueue(student);
			}
		}
		return colaResultado;
	}

	

	private boolean getDoctor(StackIF<DoctorIF> studentS, int generacion) {
		
		// caso trivial
		if(generacion==0||studentS.isEmpty()) return false;
		else{
			StackIF<DoctorIF> nuevosEstudiantes=new Stack<DoctorIF>();
			//iteramos students
			IteratorIF<DoctorIF> itstudents=studentS.iterator();
			while(itstudents.hasNext()){
				DoctorIF next=itstudents.getNext();
				if(next.getId()==this.getId()) return true;
				else{
					CollectionIF<DoctorIF> sup=((DoctorC) next).getSupervisors();
					if(sup!=null){
					IteratorIF<DoctorIF> it=sup.iterator();
					
					while(it.hasNext()){
					nuevosEstudiantes.push(it.getNext());
					}
					}
				}
			}
			generacion--;
			return getDoctor(nuevosEstudiantes, generacion);
	}
		
	}


	@Override
	public CollectionIF<DoctorIF> getSiblings() {
		QueueIF<DoctorIF> siblings=new Queue<DoctorIF>();
		// buscamos las academias superior
		AcademiaC academia=this.getAcademy();
		ListIF<DoctorIF> DoctoresSuperiores=academia.getListaDoctoresSuperiores();
		IteratorIF<DoctorIF> itdoctoresSuperiores=DoctoresSuperiores.iterator();
		while(itdoctoresSuperiores.hasNext()){
			DoctorIF doctorSuperior=itdoctoresSuperiores.getNext();
			CollectionIF<DoctorIF> estudiantes=doctorSuperior.getStudents();
			IteratorIF<DoctorIF> itestudiantes=estudiantes.iterator();
			while(itestudiantes.hasNext()){
				DoctorIF next=itestudiantes.getNext();
				if(next!=this&&!siblings.contains(next))siblings.enqueue(next);
			}
			
		}
		return siblings;
	}

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

	public CollectionIF<DoctorIF> getSupervisors() {
		AcademiaC academiaDoctor=this.getAcademy();
		return academiaDoctor.getListaDoctoresSuperiores();
		
	}

	public void setAcademia(AcademiaC a) {
		this.academia=a;
		
	}
	
	public AcademiaC getAcademy(){
		
		return academia;
	}

}
