package ${project.handlerDotDir};

import ${project.voDotDir}.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 处理所有的非自定义异常
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultVO excepionHandler(HttpServletRequest request, Exception e) {
        String url = request.getRequestURL().toString();
        ResultVO result = ResultVO.error(e.getMessage());
        log.error("请求接口" + url + "发生[[系统发生未知异常]]：{}" + e.getMessage(), e);
        return result;
    }
}
