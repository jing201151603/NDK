package com.dxhj.live.listener;

/**
 * author: 陈永镜 .
 * date: 2017/8/11 .
 * email: jing20071201@qq.com
 * <p>
 * introduce:
 */
public interface OnLiveStateChangeListener {

    /**
     * 发生错误的错误码
     * @param code
     */
    void onError(int code);

}
