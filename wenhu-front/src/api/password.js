import request from '@/utils/request'

export function loginByPassword(data) {
    return request({
        url: '/people/loginByPassword',
        method: 'post',
        data
    })
}

export function loginByVerify(data) {
    return request({
        url: '/people/loginByVerify',
        method: 'post',
        data
    })
}

export function checkPhoneIsExist(data) {
    return request({
        url: '/people/checkPhoneIsExist',
        method: 'post',
        data
    })
}

export function userRegister(data) {
    return request({
        url: '/people/userRegister',
        method: 'post',
        data
    })
}

export function resetPassword(data) {
    return request({
        url: '/people/resetPassword',
        method: 'post',
        data
    })
}
