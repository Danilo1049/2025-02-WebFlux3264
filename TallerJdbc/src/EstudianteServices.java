import java.util.List;
import java.util.Scanner;

public class EstudianteServices {
 public static void main(String[] args) {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== MENU ESTUDIANTES =====");
            System.out.println("1. Insertar Estudiante");
            System.out.println("2. Actualizar Estudiante");
            System.out.println("3. Eliminar Estudiante");
            System.out.println("4. Consultar todos los estudiantes");
            System.out.println("5. Consultar Estudiante por email");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = sc.nextLine();
                    System.out.print("Correo: ");
                    String correo = sc.nextLine();
                    System.out.print("Edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Estado Civil (SOLTERO, CASADO, VIUDO, UNION_LIBRE, DIVORCIADO): ");
                    String ec = sc.nextLine();

                    Estudiante est = new Estudiante(0, nombre, apellido, correo, edad, Estudiante.EstadoCivil.valueOf(ec.toUpperCase()));
                    app.insertar(est);
                    break;

                case 2:
                    System.out.print("Correo del estudiante a actualizar: ");
                    String correoAct = sc.nextLine();
                    System.out.print("Nuevo Nombre: ");
                    String nuevoNombre = sc.nextLine();
                    System.out.print("Nuevo Apellido: ");
                    String nuevoApellido = sc.nextLine();
                    System.out.print("Nueva Edad: ");
                    int nuevaEdad = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nuevo Estado Civil: ");
                    String nuevoEc = sc.nextLine();

                    Estudiante estAct = new Estudiante(0, nuevoNombre, nuevoApellido, correoAct, nuevaEdad, Estudiante.EstadoCivil.valueOf(nuevoEc.toUpperCase()));
                    app.actualizar(estAct);
                    break;

                case 3:
                    System.out.print("Correo del estudiante a eliminar: ");
                    String correoDel = sc.nextLine();
                    app.eliminar(correoDel);
                    break;

                case 4:
                    List<Estudiante> lista = app.listarTodos();
                    lista.forEach(System.out::println);
                    break;

                case 5:
                    System.out.print("Correo del estudiante a buscar: ");
                    String correoBus = sc.nextLine();
                    Estudiante encontrado = app.buscarPorCorreo(correoBus);
                    if (encontrado != null) {
                        System.out.println(encontrado);
                    } else {
                        System.out.println("No se encontró un estudiante con ese correo");
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 6);

        sc.close();
    }
}