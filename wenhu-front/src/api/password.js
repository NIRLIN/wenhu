import request from '@/utils/request'

export function loginByPassword(data) {
  return request({
    url: '/people/user/loginByPassword',
    method: 'post',
    data
  })
}

export function loginByVerify(data) {
  return request({
    url: '/people/user/loginByVerify',
    method: 'post',
    data
  })
}

export function checkPhoneExistAndSendSms(data) {
  return request({
    url: '/people/user/checkPhoneExistAndSendSms',
    method: 'post',
    data
  })
}

export function sendVerifyCode(data) {
  return request({
    url: '/people/user/sendVerifyCode',
    method: 'post',
    data
  })
}

export function userRegister(data) {
  return request({
    url: '/people/user/userRegister',
    method: 'post',
    data
  })
}

export function resetPassword(data) {
  return request({
    url: '/people/user/resetPassword',
    method: 'post',
    data
  })
}
