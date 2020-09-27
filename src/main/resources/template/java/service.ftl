package ${project.serviceDotDir};


import ${project.daoDotDir}.${table.nameCamel}Mapper;
import ${project.entityDotDir}.${table.nameCamel};
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ${table.nameCamel}Service extends ServiceImpl<${table.nameCamel}Mapper, ${table.nameCamel}> {
    <#if table.pkAutoIncrement == 1>
    public void saveForId(${table.nameCamel} ${table.nameCamelFirstLower}) {
        getBaseMapper().saveForId(${table.nameCamelFirstLower});
    }
    </#if>
}
