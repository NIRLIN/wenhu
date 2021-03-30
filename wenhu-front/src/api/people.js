import request from '@/utils/request'

export function getUserInfo(data) {
  return request({
    url: '/people/user/getUserInfo',
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

export function listAnswerByUserId(data) {
  return request({
    url: '/people/user/listAnswerByUserId',
    method: 'post',
    data
  })
}

export function listArticleByUserId(data) {
  return request({
    url: '/people/user/listArticleByUserId',
    method: 'post',
    data
  })
}
export function listQuestionByUserId(data) {
  return request({
    url: '/people/user/listQuestionByUserId',
    method: 'post',
    data
  })
}

export function listCollectByUserId(data) {
  return request({
    url: '/people/user/listCollectByUserId',
    method: 'post',
    data
  })
}

export function listFollowByUserId(data) {
  return request({
    url: '/people/user/listFollowByUserId',
    method: 'post',
    data
  })
}
