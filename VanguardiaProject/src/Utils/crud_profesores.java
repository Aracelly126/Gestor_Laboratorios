package Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class crud_profesores {

    public void cargarMaterias(JComboBox<String> comboMaterias, String comboBloque, String comboTipo) {
        if (comboBloque == null || comboTipo == null) {
            System.out.println("Bloque o Tipo no seleccionados.");
            return;
        }

        try {
            String idBloqueSql = "SELECT ID_BLOQUE FROM vanguardia.bloques WHERE NOMBRE = ?";
            String sql = "SELECT nombre_materia FROM vanguardia.materias "
                    + "WHERE materia_id IN ("
                    + "SELECT fk_materia FROM vanguardia.horarios h "
                    + "INNER JOIN vanguardia.espacios e ON h.fk_espacio = e.ID_ESPACIO "
                    + "WHERE e.ID_BLOQUE_PERTENECE = ? AND e.TIPO = ?"
                    + ")";
            Connection cc = Conex.getConex();
            PreparedStatement idBloqueStmt = cc.prepareStatement(idBloqueSql);
            PreparedStatement psd = cc.prepareStatement(sql);
            idBloqueStmt.setString(1, comboBloque);
            try {
                ResultSet idBloqueRs = idBloqueStmt.executeQuery();
                if (idBloqueRs.next()) {
                    int idBloque = idBloqueRs.getInt("ID_BLOQUE");
                    System.out.println("ID_BLOQUE: " + idBloque);

                    psd.setInt(1, idBloque);
                    psd.setString(2, comboTipo);

                    try {
                        ResultSet rs = psd.executeQuery();
                        comboMaterias.removeAllItems();
                        while (rs.next()) {
                            String nombreMateria = rs.getString("nombre_materia");
                            System.out.println("Materia encontrada: " + nombreMateria);
                            comboMaterias.addItem(nombreMateria);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No se encontró ninguna ID_BLOQUE para el bloque seleccionado.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarBloque(JComboBox<String> comboBloque) {
        try {
            Statement statement = Conex.getConex().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT NOMBRE FROM bloques");
            comboBloque.removeAllItems();
            while (resultSet.next()) {
                comboBloque.addItem(resultSet.getString("NOMBRE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void llenarComboBoxAulas(JComboBox cbxAulasLab, JComboBox cbxTipo, JComboBox cbxCurso) {
        cbxAulasLab.removeAllItems();
        // Verificar si se ha seleccionado un curso antes de continuar
        if (!cbxCurso.getSelectedItem().equals("Seleccionar")) {
            try {
                Connection cc = Conex.getConex();
                String idBloqueSql = "SELECT ID_BLOQUE FROM vanguardia.bloques WHERE NOMBRE = ?";
                PreparedStatement idBloqueStmt = cc.prepareStatement(idBloqueSql);
                idBloqueStmt.setString(1, cbxCurso.getSelectedItem().toString());
                ResultSet idBloqueRs = idBloqueStmt.executeQuery();

                if (idBloqueRs.next()) {
                    int idBloque = idBloqueRs.getInt("ID_BLOQUE");

                    String tipo = cbxTipo.getSelectedItem().toString();
                    String sql = "SELECT NOMBRE FROM vanguardia.espacios WHERE TIPO = ? AND ID_BLOQUE_PERTENECE = ?";
                    PreparedStatement psd = cc.prepareStatement(sql);
                    psd.setString(1, tipo.equals("LABORATORIO") ? "LABORATORIO" : "AULA");
                    psd.setInt(2, idBloque);
                    ResultSet rs = psd.executeQuery();

                    while (rs.next()) {
                        String aulaLab = rs.getString("NOMBRE");
                        cbxAulasLab.addItem(aulaLab);
                    }
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al llenar el combo box de aulas/laboratorios: " + e.getMessage());
            }
        }
    }

    public void addProfesor(String nombre, String materiaSeleccionada, String bloqueSeleccionado, String tipoSeleccionado, String aulaSeleccionada, JTable table) {
        String idblock = "SELECT ID_BLOQUE FROM BLOQUES WHERE NOMBRE = ?";
        String espacioQuery = "SELECT ID_ESPACIO FROM espacios WHERE NOMBRE = ? AND ID_BLOQUE_PERTENECE = ?";
        String materiaQuery = "SELECT materia_id FROM materias WHERE nombre_materia = ?";
        String insertQuery = "INSERT INTO profesores (nombre, fk_espacio, fk_materia) VALUES (?, ?, ?)";

        try {
            Connection connection = Conex.getConex();
            //obtener id del bloque
            int idblo = 0;
            try ( PreparedStatement bloqueStmt = connection.prepareStatement(idblock)) {
                bloqueStmt.setString(1, bloqueSeleccionado);
                ResultSet bloqueRs = bloqueStmt.executeQuery();
                if (bloqueRs.next()) {
                    idblo = bloqueRs.getInt("ID_BLOQUE");
                } else {
                    System.out.println("No se encontro ninguna ID");
                }
            }
            System.out.println(idblo);
            System.out.println(aulaSeleccionada);
            // Obtener el ID del espacio
            int espacioId = 0;
            try {
                PreparedStatement espacioStmt = connection.prepareStatement(espacioQuery);
                espacioStmt.setString(1, aulaSeleccionada);
                espacioStmt.setInt(2, idblo);
                ResultSet espacioRs = espacioStmt.executeQuery();
                if (espacioRs.next()) {
                    espacioId = espacioRs.getInt("ID_ESPACIO");
                } else {
                    System.err.println("No se encontró el espacio para el tipo y bloque proporcionados.");
                    return;
                }
            } catch (SQLException e) {

            }

            // Obtener el ID de la materia
            int materiaId = 0;
            try {
                PreparedStatement materiaStmt = connection.prepareStatement(materiaQuery);
                materiaStmt.setString(1, materiaSeleccionada);
                ResultSet materiaRs = materiaStmt.executeQuery();
                if (materiaRs.next()) {
                    materiaId = materiaRs.getInt("materia_id");
                } else {
                    System.err.println("No se encontró la materia para el nombre proporcionado.");
                    return;
                }
            } catch (SQLException e) {

            }

            System.out.println(nombre);
            // Insertar el profesor
            try {
                PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
                insertStmt.setString(1, nombre);
                insertStmt.setInt(2, espacioId);
                insertStmt.setInt(3, materiaId);
                insertStmt.executeUpdate();
                cargarTabla(table);
            } catch (SQLException e) {

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cargarTabla(JTable table) {
        try {
            String query = "SELECT p.nombre AS Profesor, m.nombre_materia AS Materia, e.TIPO, e.NOMBRE, b.NOMBRE AS BLOQUE "
                    + "FROM profesores p "
                    + "JOIN espacios e ON p.fk_espacio = e.ID_ESPACIO "
                    + "JOIN bloques b ON e.ID_BLOQUE_PERTENECE = b.ID_BLOQUE "
                    + "JOIN materias m ON p.fk_materia = m.materia_id";
            PreparedStatement preparedStatement = Conex.getConex().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {
                String profesor = resultSet.getString("Profesor");
                String materia = resultSet.getString("Materia");
                String tipo = resultSet.getString("TIPO");
                String nombre = resultSet.getString("NOMBRE");
                String bloque = resultSet.getString("BLOQUE");
                model.addRow(new Object[]{profesor, materia, tipo, nombre, bloque});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProfesor(String nombreProfesor, JTable table) {
        try {
            String sql = "DELETE FROM profesores WHERE nombre = ?";
            PreparedStatement statement = Conex.getConex().prepareStatement(sql);
            statement.setString(1, nombreProfesor);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("El profesor " + nombreProfesor + " ha sido eliminado correctamente.");
                // Recargar los datos de la tabla después de eliminar al profesor
                cargarTabla(table);
            } else {
                System.out.println("No se encontró ningún profesor con el nombre " + nombreProfesor);
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar al profesor: " + e.getMessage());
        }
    }

    public void editarProfesor(String nombre, String tipoEspacio, String nombreEspacio, String nombreMateria, String nombreProfesor) {
        int idProfesor = obtenerIdProfesor(nombreProfesor);

        if (idProfesor == -1) {
            System.out.println("No se encontró ningún profesor con el nombre proporcionado.");
            return;
        }

        String updateQuery = "UPDATE profesores SET nombre = ?, fk_espacio = (SELECT ID_ESPACIO FROM espacios WHERE TIPO = ? AND NOMBRE = ?), fk_materia = (SELECT materia_id FROM materias WHERE nombre_materia = ?) WHERE id_profesor = ?";

        try ( Connection connection = Conex.getConex();  PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {

            updateStmt.setString(1, nombre);
            updateStmt.setString(2, tipoEspacio);
            updateStmt.setString(3, nombreEspacio);
            updateStmt.setString(4, nombreMateria);
            updateStmt.setInt(5, idProfesor);
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int obtenerIdProfesor(String nombreProfesor) {
        String query = "SELECT id_profesor FROM profesores WHERE nombre = ?";
        try {
            Connection connection = Conex.getConex();  
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nombreProfesor);
            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_profesor");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Si no se encuentra el profesor, se devuelve -1
        return -1;
    }

}
