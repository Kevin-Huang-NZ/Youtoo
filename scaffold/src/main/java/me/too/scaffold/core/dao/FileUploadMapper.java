package me.too.scaffold.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.too.scaffold.core.model.FileUpload;

public interface FileUploadMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_upload
     *
     * @mbg.generated Mon Jan 18 20:26:22 NZDT 2021
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_upload
     *
     * @mbg.generated Mon Jan 18 20:26:22 NZDT 2021
     */
    int insert(FileUpload record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_upload
     *
     * @mbg.generated Mon Jan 18 20:26:22 NZDT 2021
     */
    int insertSelective(FileUpload record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_upload
     *
     * @mbg.generated Mon Jan 18 20:26:22 NZDT 2021
     */
    FileUpload selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_upload
     *
     * @mbg.generated Mon Jan 18 20:26:22 NZDT 2021
     */
    int updateByPrimaryKeySelective(FileUpload record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_upload
     *
     * @mbg.generated Mon Jan 18 20:26:22 NZDT 2021
     */
    int updateByPrimaryKey(FileUpload record);
    
    List<FileUpload> selectByTableRow(@Param("tableName") String tableName, @Param("rowId") Long rowId, @Param("deleteStatus") String deleteStatus);
    List<FileUpload> selectByDeleteStatus(@Param("deleteStatus") String deleteStatus, @Param("limit") Integer limit);
    int updateDeleteStatus(@Param("deleteStatus") String deleteStatus, @Param("ids") Long[] ids);
}