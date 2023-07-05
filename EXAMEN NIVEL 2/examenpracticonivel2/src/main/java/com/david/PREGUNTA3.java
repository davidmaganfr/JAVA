package com.david;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class PREGUNTA3 {
    public static void main(String[] args) throws SQLException {

        try{
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root", "root", "");
        }
        
    }

}

class MaterialCrud {
    private static final String SQL_INSERT_MATERIAL = "INSERT INTO material VALUES (?)";
    private static final String SQL_DELETE_MATERIAL = "DELETE FROM material WHERE id=?";
    private static final String SQL_UPDATE_MATERIAL = "UPDATE material SET nombre=?, tipo=? WHERE id=?";
    private static final String SQL_SELECT_MATERIAL = "SELECT * FROM material WHERE id=?";

    private static RowMapper<Material> mapperCliente = new RowMapper<>() {
        @Override
        @Nullable
        public Material mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Material(rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo"));
        }
    };

    private JdbcTemplate template;

    public MaterialCrud(JdbcTemplate template) {
        this.template = template;
    }

    public void create(Material material) {
        template.update(SQL_INSERT_MATERIAL, material.getId(), material.getNombre(), material.getTipoMaterial());
    }

    public Material read(int id) {
        try {
            return template.queryForObject(SQL_SELECT_MATERIAL, mapperCliente, id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void update(Material material) {
        template.update(SQL_UPDATE_MATERIAL, material.getNombre(), material.getTipoMaterial(), material.getId());
    }

    public void delete(int id) {
        template.update(SQL_DELETE_MATERIAL, id);
    }
}