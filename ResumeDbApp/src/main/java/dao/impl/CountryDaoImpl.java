package dao.impl;

import dao.inter.AbstractDao;
import dao.inter.CountryDaoInter;
import entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImpl extends AbstractDao implements CountryDaoInter {

    public Country getCountry(ResultSet rs) throws SQLException {

        int id = rs.getInt("Id");
        String name = rs.getString("name");
        String nationality = rs.getString("nationality");

        Country country = new Country(id, name, nationality);
        System.out.println(country);
        return country;

    }

    @Override
    public List<Country> getAll() {
        List<Country> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            Statement stmt = conn.createStatement();
            stmt.execute("SELECT * FROM country");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                Country country = getCountry(rs);
                list.add(country);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    @Override
    public Country getById(int userId) {
        Country el = null;
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM country WHERE ID = ?");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                el = getCountry(rs);

            }
        } catch (Exception ex) {

        }
        return el;
    }

    @Override
    public boolean updateCountry(Country u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE country SET name=?,nationality=? WHERE id= ?");
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getNationality());
            stmt.setInt(3, u.getId());
            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeCountry(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM country WHERE id=?;");
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (Exception ex) {
            return false;
        }
    }

}
