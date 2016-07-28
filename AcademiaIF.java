/* Representación de una Academia formada por una colección de Doctores */
public interface AcademiaIF extends CollectionIF<DoctorIF> {
/* Consulta el Doctor que fundó la Academia */
/* @returns el Doctor fundador de la Academia */
public DoctorIF getFounder();
/* Busca un Doctor dentro de la Academia a partir de su identificador */
/* @pre el doctor pertenece a la Academia && id > 0 */
/* @param el identificador del Doctor a buscar */
/* @returns el Doctor buscado */
public DoctorIF getDoctor(int id);
/* Consulta el número de Doctores que pertenecen a la Academia */
/* @returns el número de Doctores pertenecientes a la Academia */
public int size();
/* Añade un nuevo Doctor a la Academia a partir de la lectura de su Tesis */
/* @param el nuevo Doctor y su primer director de Tesis */
/* @pre el nuevo doctor no debe pertenecer a la Academia && */
/* el supervisor sí debe pertenecer a la Academia */
public void addDoctor (DoctorIF newDoctor, DoctorIF supervisor);
/* Añade una relación de dirección al Árbol Genealógico de la Academia */
/* @param el nuevo Doctor y uno de sus codirectores de Tesis */
/* @pre ambos doctores deben pertenecer a la Academia && */
/* no existe una relación de supervisión previa entre ambos */
public void addSupervision (DoctorIF student, DoctorIF supervisor);
}