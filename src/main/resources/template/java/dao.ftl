package ${project.daoDotDir};

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${project.entityDotDir}.${table.nameCamel};
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface ${table.nameCamel}Mapper extends BaseMapper<${table.nameCamel}> {
    <#if table.pkAutoIncrement == 1>
    void saveForId(${table.nameCamel} ${table.nameCamelFirstLower});
    </#if>
}