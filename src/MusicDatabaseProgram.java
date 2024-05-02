import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MusicDatabaseProgram {
    // Credenciales de la base de datos
    static final String DB_URL = "jdbc:mysql://localhost/music";
    static final String USER = "root";
    static final String PASS = "56946848654";

    public static void main(String[] args) {
        try {
            // Conexión a la base de datos
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Agregar un Artista a la Base de Datos
            insertArtist(conn, "Jesse y Joy");

            // Agregar un Álbum a la Base de Datos asociado con el artista creado
           // int artistId = getArtistId(conn, "Jesse y Joy");
           // insertAlbum(conn, artistId, "Aire");



            conn.close(); // Cerrar conexión
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un artista en la base de datos
    static void insertArtist(Connection conn, String artistName) throws SQLException {
        String sql = "INSERT INTO artists (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, artistName);
            pstmt.executeUpdate();
        }
    }

    // Método para obtener el ID de un artista por su nombre
    static int getArtistId(Connection conn, String artistName) throws SQLException {
        String sql = "SELECT id FROM artists WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, artistName);
            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        }
        return -1; // Si no se encuentra el artista
    }

    // Método para insertar un álbum en la base de datos asociado con un artista
  /*  static void insertAlbum(Connection conn, int artistId, String albumName) throws SQLException {
        String sql = "INSERT INTO albums (artist_id, name) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, artistId);
            pstmt.setString(2, albumName);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar un artista de la base de datos y sus álbumes asociados
    static void deleteArtist(Connection conn, String artistName) throws SQLException {
        String sql = "DELETE FROM artists WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, artistName);
            pstmt.executeUpdate();
        }
    }

    // Método para actualizar el nombre de un álbum en la base de datos
    static void updateAlbum(Connection conn, String oldAlbumName, String newAlbumName) throws SQLException {
        String sql = "UPDATE albums SET name = ? WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newAlbumName);
            pstmt.setString(2, oldAlbumName);
            pstmt.executeUpdate();
        }
    }

    // Método para insertar un artista y un álbum simultáneamente
    static void insertArtistAlbum(Connection conn, String artistName, String albumName) throws SQLException {
        conn.setAutoCommit(false); // Deshabilitar la confirmación automática para transacción
        try {
            insertArtist(conn, artistName); // Insertar artista
            int artistId = getArtistId(conn, artistName); // Obtener ID del artista insertado
            insertAlbum(conn, artistId, albumName); // Insertar álbum asociado al artista
            conn.commit(); // Confirmar transacción
        } catch (SQLException e) {
            conn.rollback(); // Revertir cambios en caso de error
            throw e;
        } finally {
            conn.setAutoCommit(true); // Restaurar confirmación automática
        }
    }

   */
}
