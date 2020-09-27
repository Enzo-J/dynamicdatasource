package ${project.voDotDir};

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ResultVO<T> {

    /**
    * 返回请求结果的状态
    */
    @ApiModelProperty(value = "响应状态码", dataType = "String", name = "code", example = "200")
    private String code;

    /**
    * 返回请求结果的message
    */
    @ApiModelProperty(value = "响应消息", dataType = "String", name = "message", position = 1, example = "请求成功")
    private String message;

    /**
    * 封装具体数据
    */
    @ApiModelProperty(value = "响应结果", dataType = "Object", name = "data", position = 2)
    private T data;

    public ResultVO() {

    }

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("200");
        resultVO.setMessage("请求成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success(Object object, Integer total) {
        total = total == null ? 0 : total;
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("200");
        resultVO.setMessage("请求成功");
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(String code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }

    public static ResultVO error(String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode("10001");
        resultVO.setMessage(message);
        return resultVO;
    }
}
