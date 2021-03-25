import request from '@/utils/request'

export function getUserInfo(data) {
  return request({
    url: '/people/user/getUserInfo',
    method: 'post',
    data
  })
}
export function listCreationById(data) {
  return request({
    url: '/people/user/listCreationById',
    method: 'post',
    data
  })
}

export function getHomepageByUserId(data) {
  return request({
    url: '/people/user/getHomepageByUserId',
    method: 'post',
    data
  })
}
export function getUserHomepageDataByUserId(data) {
  return request({
    url: '/people/user/getUserHomepageDataByUserId',
    method: 'post',
    data
  })
}
