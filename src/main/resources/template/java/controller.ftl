package ${project.controllerDotDir};

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Maps;
import ${project.dtoDotDir}.WrapperOper;
import ${project.entityDotDir}.${table.nameCamel};
import ${project.voDotDir}.ResultVO;
import ${project.serviceDotDir}.${table.nameCamel}Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

<#if table.remark == "">
@Api(tags = "${table.name}表相关API")
<#else >
@Api(tags = "${table.remark}(${table.name})API")
</#if>
@RestController
@RequestMapping("/${table.nameCamelFirstLower}")
public class ${table.nameCamel}Controller {
    @Autowired
    private ${table.nameCamel}Service ${table.nameCamelFirstLower}Service;

    @ApiOperation(value = "查询列表")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @GetMapping("/list")
    public ResultVO<List<${table.nameCamel}>> list() {
        return ResultVO.success(${table.nameCamelFirstLower}Service.list());
    }

    @ApiOperation(value = "保存或者更新")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/saveOrUpdate", consumes = {"application/json"})
    public ResultVO saveOrUpdate(@RequestBody ${table.nameCamel} ${table.nameCamelFirstLower}) {
        return ResultVO.success(${table.nameCamelFirstLower}Service.saveOrUpdate(${table.nameCamelFirstLower}));
    }

    @ApiOperation(value = "批量插入")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/saveBatch", consumes = {"application/json"})
    public ResultVO saveBatch(@RequestBody List<${table.nameCamel}> entityList) {
    return ResultVO.success(${table.nameCamelFirstLower}Service.saveBatch(entityList));
    }

    @ApiOperation(value = "批量插入或更新")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/saveOrUpdateBatch", consumes = {"application/json"})
    public ResultVO saveOrUpdateBatch(@RequestBody List<${table.nameCamel}> entityList) {
        return ResultVO.success(${table.nameCamelFirstLower}Service.saveBatch(entityList));
    }

    @ApiOperation(value = "根据实体非空属性删除")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/removeByEntity", consumes = {"application/json"})
    public ResultVO removeByEntity(@RequestBody Map<String, Object> entity) {
        if (entity == null) {
            return ResultVO.error("缺少请求参数");
        }
        Map<String, Object> map = extractColumnValMap(entity);
        if (map.size() == 0) {
            return ResultVO.error("缺少请求参数");
        }
        return ResultVO.success(${table.nameCamelFirstLower}Service.removeByMap(map));
    }


    @ApiOperation(value = "根据实体非空属性查询")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/listByEntity", consumes = {"application/json"})
    public ResultVO listByMap(@RequestBody Map<String, Object> entity) {
        if (entity == null) {
            return ResultVO.error("缺少请求参数");
        }
        Map<String, Object> map = extractColumnValMap(entity);
        if (map.size() == 0) {
            return ResultVO.error("缺少请求参数");
        }
        return ResultVO.success(${table.nameCamelFirstLower}Service.listByMap(map));
    }

    private Map<String, Object> extractColumnValMap(Map<String, Object> entity) {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(entity.size());
        for (String fieldName : entity.keySet()) {
            Object val = entity.get(fieldName);
            String columnName = ${table.nameCamel}.getColumnByField(fieldName);
            if (fieldName != null && val != null && columnName != null ) {
                map.put(columnName, val);
            }
        }
        return map;
    }

    <#if table.pkAutoIncrement == 0>
    @ApiOperation(value = "保存一条数据，返回是否成功标志")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResultVO<Boolean> save(@RequestBody ${table.nameCamel} ${table.nameCamelFirstLower}) {
        return ResultVO.success(${table.nameCamelFirstLower}Service.save(${table.nameCamelFirstLower}));
    }
    <#else>
    @ApiOperation(value = "保存一条数据,返回主键")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/save", consumes = {"application/json"})
    public ResultVO<Serializable> save(@RequestBody ${table.nameCamel} ${table.nameCamelFirstLower}) {
        if (${table.nameCamelFirstLower}.get${table.pkFieldName?cap_first}() != null) {
            return ResultVO.error("主键必须为空");
        }
        ${table.nameCamelFirstLower}Service.saveForId(${table.nameCamelFirstLower});
        return ResultVO.success(${table.nameCamelFirstLower}.get${table.pkFieldName?cap_first}());
    }

    </#if>

    <#if table.primaryKeyCount == 1>

    @ApiOperation(value = "根据主键获取数据")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @GetMapping("/getById")
    public ResultVO<${table.nameCamel}> getById(@RequestParam Serializable id) {
        return ResultVO.success(${table.nameCamelFirstLower}Service.getById(id));
    }

    @ApiOperation(value = "根据主键删除")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @GetMapping("/removeById")
    public ResultVO removeById(@RequestParam Serializable id) {
        return ResultVO.success(${table.nameCamelFirstLower}Service.removeById(id));
    }

    @ApiOperation(value = "根据主键更新")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/updateById", consumes = {"application/json"})
    public ResultVO updateById(@RequestBody ${table.nameCamel} ${table.nameCamelFirstLower}) {
        return ResultVO.success(${table.nameCamelFirstLower}Service.updateById(${table.nameCamelFirstLower}));
    }

    @ApiOperation(value = "根据主键批量删除")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/removeByIds", consumes = {"application/json"})
    public ResultVO removeByIds(@RequestBody List<Serializable> ids) {
        return ResultVO.success(${table.nameCamelFirstLower}Service.removeByIds(ids));
    }


    @ApiOperation(value = "根据主键批量更新")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/updateBatchById", consumes = {"application/json"})
    public ResultVO updateBatchById(@RequestBody List<${table.nameCamel}> entityList) {
        return ResultVO.success(${table.nameCamelFirstLower}Service.updateBatchById(entityList));
    }

    @ApiOperation(value = "根据主键批量查询")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/listByIds", consumes = {"application/json"})
    public ResultVO<List<${table.nameCamel}>> listByIds(@RequestBody List<Serializable> ids) {
        return ResultVO.success(${table.nameCamelFirstLower}Service.listByIds(ids));
    }
    </#if>

    @ApiOperation(value = "根据条件删除")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/remove", consumes = {"application/json"})
    public ResultVO<Boolean> remove(@RequestBody List<WrapperOper> wrapperOpers) {
        QueryWrapper<${table.nameCamel}> queryWrapper = new QueryWrapper();
        wrapperExtract(queryWrapper, wrapperOpers);
        return ResultVO.success(${table.nameCamelFirstLower}Service.remove(queryWrapper));
    }

    @ApiOperation(value = "根据条件更新")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/update", consumes = {"application/x-www-form-urlencoded"})
    public ResultVO<Boolean> update(@RequestParam String setSql, @RequestParam String wrapperOpers) {
        UpdateWrapper<${table.nameCamel}> updateWrapper = new UpdateWrapper<>();
        List<WrapperOper> wo = JSONObject.parseArray(wrapperOpers, WrapperOper.class);
        wrapperExtract(updateWrapper, wo);
        updateWrapper.setSql(true, setSql);
        return ResultVO.success(${table.nameCamelFirstLower}Service.update(updateWrapper));
    }


    @ApiOperation(value = "根据条件更新")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/updateByEntity", consumes = {"application/x-www-form-urlencoded"})
    public ResultVO<Boolean> updateByEntity(@RequestParam String entity, @RequestParam String wrapperOpers) {
        UpdateWrapper<${table.nameCamel}> updateWrapper = new UpdateWrapper<>();
        ${table.nameCamel} ${table.nameCamelFirstLower} = JSONObject.parseObject(entity, ${table.nameCamel}.class);
        List<WrapperOper> wo = JSONObject.parseArray(wrapperOpers, WrapperOper.class);
        wrapperExtract(updateWrapper, wo);
        return ResultVO.success(${table.nameCamelFirstLower}Service.update(${table.nameCamelFirstLower}, updateWrapper));
    }

    @ApiOperation(value = "根据wrapper查询一条记录,有多条抛出异常")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/getOne", consumes = {"application/json"})
    public ResultVO<${table.nameCamel}> getOne(@RequestBody List<WrapperOper> wrapperOpers) {
        QueryWrapper<${table.nameCamel}> queryWrapper = new QueryWrapper<>();
        wrapperExtract(queryWrapper, wrapperOpers);
        return ResultVO.success(${table.nameCamelFirstLower}Service.getOne(queryWrapper));
    }

    @ApiOperation(value = "根据wrapper查询一条记录,有多条抛出异常")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/getMap", consumes = {"application/x-www-form-urlencoded"})
    public ResultVO<Map<String, Object>> getMap(@RequestParam String selectSql, @RequestParam String wrapperOpers) {
        QueryWrapper<${table.nameCamel}> queryWrapper = new QueryWrapper<>();
        List<WrapperOper> wo = JSONObject.parseArray(wrapperOpers, WrapperOper.class);
        wrapperExtract(queryWrapper, wo);
        queryWrapper.select(selectSql);
        return ResultVO.success(${table.nameCamelFirstLower}Service.getMap(queryWrapper));
    }

    @ApiOperation(value = "根据wrapper查询总条数")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/count", consumes = {"application/json"})
    public ResultVO<Integer> count(@RequestBody List<WrapperOper> wrapperOpers) {
        QueryWrapper<${table.nameCamel}> queryWrapper = new QueryWrapper<>();
        wrapperExtract(queryWrapper, wrapperOpers);
        return ResultVO.success(${table.nameCamelFirstLower}Service.count(queryWrapper));
    }

    @ApiOperation(value = "根据wrapper查询List")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/listByWrapper", consumes = {"application/json"})
    public ResultVO<List<${table.nameCamel}>> listByWrapper(@RequestBody(required = false) List<WrapperOper> wrapperOpers) {
        QueryWrapper<${table.nameCamel}> queryWrapper = new QueryWrapper<>();
        wrapperExtract(queryWrapper, wrapperOpers);
        return ResultVO.success(${table.nameCamelFirstLower}Service.list(queryWrapper));
    }


    @ApiOperation(value = "根据wrapper查询分页")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/pageByWrapper", consumes = {"application/x-www-form-urlencoded"})
    public ResultVO<Page<${table.nameCamel}>> page(@RequestParam int current, @RequestParam int size, @RequestParam(required = false) String wrapperOpers) {
        QueryWrapper<${table.nameCamel}> queryWrapper = new QueryWrapper<>();
        List<WrapperOper> wo = JSONObject.parseArray(wrapperOpers, WrapperOper.class);
        wrapperExtract(queryWrapper, wo);
        return ResultVO.success(${table.nameCamelFirstLower}Service.page(new Page<>(current, size), queryWrapper));
    }


    @ApiOperation(value = "根据wrapper查询List")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/listMapsByWrapper", consumes = {"application/x-www-form-urlencoded"})
    public ResultVO<List<Map<String, Object>>> listMapsByWrapper(@RequestParam String selectSql, @RequestParam(required = false) String wrapperOpers) {
        QueryWrapper<${table.nameCamel}> queryWrapper = new QueryWrapper<>();
        List<WrapperOper> wo = JSONObject.parseArray(wrapperOpers, WrapperOper.class);
        wrapperExtract(queryWrapper, wo);
        queryWrapper.select(selectSql);
        return ResultVO.success(${table.nameCamelFirstLower}Service.listMaps(queryWrapper));
    }

    @ApiOperation(value = "根据wrapper查询分页")
    @ApiResponses({@ApiResponse(code = 200, message = "处理成功"), @ApiResponse(code = 10001, message = "处理失败")})
    @PostMapping(value = "/pageMapsByWrapper", consumes = {"application/x-www-form-urlencoded"})
    public ResultVO<Page<Map<String,Object>>> pageMapsByWrapper(@RequestParam int current, @RequestParam int size, @RequestParam String selectSql, @RequestParam(required = false) String wrapperOpers) {
        QueryWrapper<${table.nameCamel}> queryWrapper = new QueryWrapper<>();
        List<WrapperOper> wo = JSONObject.parseArray(wrapperOpers, WrapperOper.class);
        wrapperExtract(queryWrapper, wo);
        queryWrapper.select(selectSql);
        return ResultVO.success(${table.nameCamelFirstLower}Service.pageMaps(new Page<>(current, size), queryWrapper));
    }

    private void wrapperExtract(AbstractWrapper abstractWrapper, List<WrapperOper> wrapperOpers) {
        if (wrapperOpers == null || wrapperOpers.isEmpty()) {
            return;
        }
        Map<String, String> map = ${table.nameCamel}.getFieldToColumn();
        for (WrapperOper wrapperOper : wrapperOpers) {
            wrapperOper.extract(abstractWrapper, map);
        }
    }
}
