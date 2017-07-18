package cn.ffcs.repository;

import cn.ffcs.model.RoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * Created by MәӧωρaЯsε on 2017/6/19.
 */
@Repository
public class SysRoleUserRepository {
    private static final Logger logger = LoggerFactory.getLogger(SysRoleUserRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RoleModel> getRoleByUserId(int userId) {
        String queryString = "select sys_role.`name`,sys_role_user.sys_role_id from sys_role_user " +
                "LEFT JOIN sys_role ON sys_role.id = sys_role_user.sys_role_id " +
                "where sys_user_id = ?";

        return (List<RoleModel>)jdbcTemplate.query(queryString,
                new Object[]{userId}, new RoleRowMapper());
    }

    private class RoleRowMapper implements RowMapper<RoleModel> {
        @Override
        public RoleModel mapRow(ResultSet resultSet, int i) throws SQLException {

            return new RoleModel(resultSet.getInt("sys_role_id"),resultSet.getString("name"));
        }
    }
}
