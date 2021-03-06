package me.too.scaffold.core.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import me.too.scaffold.core.model.User;
import com.github.pagehelper.Page;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Dec 27 00:01:22 NZDT 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Dec 27 00:01:22 NZDT 2020
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Dec 27 00:01:22 NZDT 2020
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Dec 27 00:01:22 NZDT 2020
     */
    User selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Dec 27 00:01:22 NZDT 2020
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user
     *
     * @mbg.generated Sun Dec 27 00:01:22 NZDT 2020
     */
    int updateByPrimaryKey(User record);
    

    User selectByPhone(@Param("phone") String phone, @Param("notThisId") Long notThisId);
	Page<User> selectPaged(Map<String, Object> criteria);
}