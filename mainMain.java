
public class mainMain {
	public static void main(String[] args) {
		DoctorS founder = new DoctorS(1);
		AcademiaS a = new AcademiaS();
		a.setFounder(founder);
		founder.setAcademia(a);
	
		DoctorS d2=new DoctorS(2,a,a.getDoctor(1));
		a.addDoctor(d2, d2.getSupervisor());
		DoctorS d3=new DoctorS(3,a,a.getDoctor(2));
		a.addDoctor(d3, d3.getSupervisor());
		DoctorS d4=new DoctorS(4,a,a.getDoctor(2));
		a.addDoctor(d4, d4.getSupervisor());
		DoctorS d5=new DoctorS(5,a,a.getDoctor(4));
		a.addDoctor(d5, d5.getSupervisor());
		DoctorS d6=new DoctorS(6,a,a.getDoctor(2));
		a.addDoctor(d6, d6.getSupervisor());
		DoctorS d7=new DoctorS(7,a,a.getDoctor(3));
		a.addDoctor(d7, d7.getSupervisor());
		
		IteratorIF<DoctorIF> i=d7.getAncestors(2).iterator();
		while(i.hasNext()){
			System.out.println(i.getNext().getId()+"iii");
		}

		System.out.println("ver");
		//System.out.println(d7.getSupervisor().getId());
		//System.out.println(a.size());**/
	
	}
}
