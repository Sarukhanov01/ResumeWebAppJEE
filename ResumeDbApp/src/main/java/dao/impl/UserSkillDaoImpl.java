package dao.impl;

import dao.inter.AbstractDao;
import dao.inter.UserSkillDaoInter;
import entity.Skill;
import entity.User;
import entity.UserSkill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    public UserSkill getUserSkill(ResultSet rs) throws SQLException {
        int userSkillId = rs.getInt("userSkillId");
        int userId = rs.getInt("id");
        int skill_id = rs.getInt("skill_id");
        String skill_name = rs.getString("skill_name");
        int power = rs.getInt("power");
        UserSkill us = new UserSkill(userSkillId, new User(userId), new Skill(skill_id, skill_name), power);
        System.out.println(us);
        return us;

    }

    @Override
    public List<UserSkill> getAllSkillByUserId(int id) {
        List<UserSkill> list = new ArrayList<>();
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("SELECT "
                    + " us.id as userSkillId, "
                    + " u.*,"
                    + " us.skill_id,s.name AS Skill_name ,"
                    + " us.power "
                    + " FROM "
                    + " user_skill us "
                    + " LEFT JOIN user u ON us.user_id1=u.id "
                    + " LEFT JOIN skill s ON us.skill_id=s.id "
                    + " WHERE us.user_id1 = ? ");
            stmt.setInt(1, id);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {

                UserSkill us = getUserSkill(rs);
                list.add(us);

            }
        } catch (Exception ex) {

        }
        return list;
    }

    public boolean insertUserSkill(UserSkill u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO user_skill (skill_id , user_id1 ,power) VALUES (? , ? ,  ? ) ;");

            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    public boolean updateUserSkill(UserSkill u) {
        Connection conn;
        boolean b = true;
        try {
            conn = connect();
            PreparedStatement stmt = conn.prepareStatement("UPDATE user_skill SET skill_id = ? , user_id1 =? ,power =?  WHERE id = ? ;");

            stmt.setInt(1, u.getSkill().getId());
            stmt.setInt(2, u.getUser().getId());
            stmt.setInt(3, u.getPower());

            stmt.setInt(4, u.getId());

            b = stmt.execute();

        } catch (Exception ex) {
            System.err.println(ex);
            b = false;
        }
        return b;
    }

    @Override
    public boolean removeUser(int id) {
        Connection conn;
        try {
            conn = connect();

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM user_skill WHERE ID=?");
            stmt.setInt(1, id);
            System.out.println("id :" + String.valueOf(id));
            return stmt.execute();

        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

}