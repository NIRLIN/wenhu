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

export function userCollectArticle(data) {
  return request({
    url: '/people/collect/userCollectArticle',
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

export function userAgreeArticle(data) {
  return request({
    url: '/creation/article/userAgreeArticle',
    method: 'post',
    data
  })
}
export function userOpposeArticle(data) {
  return request({
    url: '/creation/article/userOpposeArticle',
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
export function getUserCollectArticle(data) {
  return request({
    url: '/creation/article/getUserCollectArticle',
    method: 'post',
    data
  })
}
export function getArticleByArticleId(data) {
  return request({
    url: '/creation/article/getArticleByArticleId',
    method: 'post',
    data
  })
}
