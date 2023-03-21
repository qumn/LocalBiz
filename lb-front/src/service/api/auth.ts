import { authRequest, systemRequest } from './../request/index';
import { apiRequest } from '../request';

/**
 * 获取验证码
 * @param phone - 手机号
 * @returns - 返回boolean值表示是否发送成功
 */
export const fetchCode = () => {
  return apiRequest.get('/code');
}


/**
 * 登录
 * @param userName - 用户名
 * @param password - 密码
 */
export function fetchLogin<T extends ApiAuth.LoginUser>(user: T) {
  return authRequest.post<ApiAuth.Token>('/login', user);
}

/** 获取用户信息 */
export function fetchUserInfo() {
  return systemRequest.get('/user/getInfo');
}

/**
 * 获取用户路由数据
 * @param userId - 用户id
 * @description 后端根据用户id查询到对应的角色类型，并将路由筛选出对应角色的路由数据返回前端
 */
export function fetchUserRoutes(userId: string) {
  //return mockRequest.post<ApiRoute.Route>('/getUserRoutes', { userId });
}

/**
 * 刷新token
 * @param refreshToken
 */
export function fetchUpdateToken(refreshToken: string) {
  //return mockRequest.post<ApiAuth.Token>('/updateToken', { refreshToken });
}
