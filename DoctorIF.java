/* Representación de un Doctor perteneciente a una Academia */
public interface DoctorIF {
/* Consulta los ancestros académicos del doctor, limitándose al número de */
/* generaciones indicado por el parámetro. */
/* @returns la colección de ancestros académicos del doctor limitada al */
/* número de generaciones indicado o hasta llegar al fundador de la */
/* Academia. No deberá contener repeticiones. */
/* @param número de generaciones a considerar */
/* @pre generations > 0 */
public CollectionIF<DoctorIF> getAncestors(int generations);
/* Consulta los doctores a quienes el doctor ha dirigido sus Tesis. */
/* @returns la colección de doctores cuyo director de tesis es el doctor. */
public CollectionIF<DoctorIF> getStudents();
/* Consulta los descendientes académicos del doctor, limitándose al número */
/* de generaciones indicado por el parámetro. */
/* @returns la colección de descendientes académicos del doctor limitada */
/* al número de generaciones indicado o hasta llegar a Doctores que no */
/* hayan dirigido ninguna Tesis. No deberá contener repeticiones. */
/* @param número de generaciones a considerar */
/* @pre generations > 0 */
public CollectionIF<DoctorIF> getDescendants(int generations);

/* Consulta los doctores que comparten director de tesis con el doctor. */
/* @returns la colección de hermanos académicos del doctor. No deberá */
/* contener repeticiones ni al doctor llamante */
public CollectionIF<DoctorIF> getSiblings();
/* Obtiene el identificador del Doctor */
/* @returns el identificador entero del Doctor, único en la Academia */
public int getId();
}