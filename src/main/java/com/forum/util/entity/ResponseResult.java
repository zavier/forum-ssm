package com.forum.util.entity;

import java.util.List;

public class ResponseResult<T> {

    /**
     * 处理结果成功
     */
    public static final String RES_SUCCESS = "success";
    
    /**
     * 处理结果失败
     */
    public static final String RES_FAIL = "fail";
    
	//处理结果状态码
	private String stateCode;
	
	//结果信息
	private String message;
	
	//结果数据
	private List<T> result;

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
	
}
