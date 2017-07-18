package cn.ffcs.repository;

import cn.ffcs.model.PermissionModel;
import cn.ffcs.model.RoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MәӧωρaЯsε on 2017/5/3.
 */
@Repository
public class SysPermissionRepository {

    private static final Logger logger = LoggerFactory.getLogger(SysPermissionRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<PermissionModel> getPermissionByUserId(String permission_ids) {
        String queryString = "SELECT `name`,id,description,pid FROM sys_permission " +
                "WHERE id IN ( "+ permission_ids +"  )";

        return jdbcTemplate.query(queryString,
                new Object[]{}, new PermissionRowMapper());
    }

    private class PermissionRowMapper implements RowMapper<PermissionModel> {
        @Override
        public PermissionModel mapRow(ResultSet resultSet, int i) throws SQLException {

            return new PermissionModel(resultSet.getInt("id"),resultSet.getString("name"),
                                       resultSet.getString("description"),resultSet.getInt("pid"));
        }
    }

    public List<PermissionModel> findMenu(){
        String queryString = "SELECT * FROM sys_permission WHERE pid = 0";

        return jdbcTemplate.query(queryString,
                new Object[]{}, new PermissionRowMapper());
    }

    public List<PermissionModel> findMenu(int pid){
        String queryString = "SELECT * FROM sys_permission WHERE pid = ?";

        return jdbcTemplate.query(queryString,
                new Object[]{pid}, new PermissionRowMapper());
    }

}
