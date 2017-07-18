package cn.ffcs.repository;

import cn.ffcs.domain.SysUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * User类的CRUD操作
 * @see SysUser
 * @author ScienJus
 * @date 2015/7/10.
 */
@Repository
public interface UserRepository extends CrudRepository<SysUser, Integer> {

//    public SysUser findByUsername(String username);
//
//    @Modifying
//    @Transactional
//    @Query(value = "update sys_user set nickname = ? where id = ? ",nativeQuery = true)
//    public int updateNickname(String nickname,Long userid);


}
