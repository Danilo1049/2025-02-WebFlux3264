import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String URL = "jdbc:mysql://localhost:3306/tallerjdbc?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";  

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertar(Estudiante est) {
        String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, est.getNombre());
            stmt.setString(2, est.getApellido());
            stmt.setString(3, est.getCorreo());
            stmt.setInt(4, est.getEdad());
            stmt.setString(5, est.getEstadoCivil().name());
            stmt.executeUpdate();
            System.out.println("Estudiante insertado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizar(Estudiante est) {
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, est.getNombre());
            stmt.setString(2, est.getApellido());
            stmt.setInt(3, est.getEdad());
            stmt.setString(4, est.getEstadoCivil().name());
            stmt.setString(5, est.getCorreo());
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Estudiante actualizado");
            } else {
                System.out.println("No se encontró un estudiante con ese correo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(String correo) {
        String sql = "DELETE FROM estudiantes WHERE correo=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            int filas = stmt.executeUpdate();
            if (filas > 0) {
                System.out.println("Estudiante eliminado");
            } else {
                System.out.println("No se encontró un estudiante con ese correo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Estudiante> listarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Estudiante est = new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo"),
                    rs.getInt("edad"),
                    Estudiante.EstadoCivil.valueOf(rs.getString("estado_civil"))
                );
                lista.add(est);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Estudiante buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("correo"),
                    rs.getInt("edad"),
                    Estudiante.EstadoCivil.valueOf(rs.getString("estado_civil"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
