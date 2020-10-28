package mvcPure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RoomDAO {

    private Connection con;

    public RoomDAO() {
        String dbUrl = "jdbc:mysql://localhost:3306/hotel";
        String dbClass = "com.mysql.jdbc.Driver";
    }

    public void addRoom(Room room) {
        String sql = "INSERT INTO tblRoom(id, name, type, displayPrice,
description
        ) VALUES( ?,  ?,  ?,  ?,  ?)
        ";
try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, room.getId());
            ps.setString(2, room.getName());
            ps.setString(3, room.getType());
            ps.setFloat(4, room.getDisplayPrice());
            ps.setString(5, room.getDescription());
        }
    
}
