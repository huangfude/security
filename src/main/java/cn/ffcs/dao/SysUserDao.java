package cn.ffcs.dao;

import cn.ffcs.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by MәӧωρaЯsε on 2017/5/5.
 */
public interface SysUserDao extends JpaRepository<SysUser, Long>{

    SysUser findByUsername(String username);

    @Modifying
    @Transactional
    @Query(value = "update sys_user set nickname = ? where id = ? ",nativeQuery = true)
    int updateNickname(String nickname,Long userid);
}
