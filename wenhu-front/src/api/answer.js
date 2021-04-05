import request from '@/utils/request'

export function countAnswerByQuestionId(data) {
  return request({
    url: '/creation/answer/countAnswerByQuestionId',
    method: 'post',
    data
  })
}

export function listAnswerByHeat(data) {
  return request({
    url: '/creation/answer/listAnswerByHeat',
    method: 'post',
    data
  })
}

export function listAnswerByTime(data) {
  return request({
    url: '/creation/answer/listAnswerByTime',
    method: 'post',
    data
  })
}

export function getAnswerByAnswerId(data) {
  return request({
    url: '/creation/answer/getAnswerByAnswerId',
    method: 'post',
    data
  })
}

export function saveAnswer(data) {
  return request({
    url: '/creation/answer/saveAnswer',
    method: 'post',
    data
  })
}

export function userAgreeAnswer(data) {
  return request({
    url: '/creation/answer/userAgreeAnswer',
    method: 'post',
    data
  })
}
export function userOpposeAnswer(data) {
  return request({
    url: '/creation/answer/userOpposeAnswer',
    method: 'post',
    data
  })
}

export function userCollectAnswer(data) {
  return request({
    url: '/people/collect/userCollectAnswer',
    method: 'post',
    data
  })
}

export function getUserAgreeAndCollectAnswer(data) {
  return request({
    url: '/creation/answer/getUserAgreeAndCollectAnswer',
    method: 'post',
    data
  })
}

export function saveAgreeArticleByUserId(data) {
  return request({
    url: '/creation/article/saveAgreeArticleByUserId',
    method: 'post',
    data
  })
}
export function saveOpposeArticleByUserId(data) {
  return request({
    url: '/creation/article/saveOpposeArticleByUserId',
    method: 'post',
    data
  })
}

export function getUserAgreeArticle(data) {
  return request({
    url: '/creation/article/getUserAgreeArticle',
    method: 'post',
    data
  })
}
