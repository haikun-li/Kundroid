package com.augurit.kundroid.data

/**
 *
 * 包名：com.augurit.agmobile.data
 * 类描述：后端返回结果包装类
 * 创建人：haikun
 * 创建时间：2021/1/15 15:02
 * 修改人：haikun
 * 修改时间：2021/1/15 15:02
 * 修改备注：
 * @version
 *
 */
data class AppResponse<T>(val data:T?, val code:Int, val message:String)