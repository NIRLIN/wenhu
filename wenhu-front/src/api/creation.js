import request from '@/utils/request'

export function saveQuestion(data) {
  return request({
    url: '/creation/question/saveQuestion',
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

export function getQuestionById(data) {
  return request({
    url: '/creation/question/getQuestionById',
    method: 'post',
    data
  })
}
export function getAnswerListByQuestionId(data) {
  return request({
    url: '/creation/question/getQuestionAndAnswerListByQuestionId',
    method: 'post',
    data
  })
}

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

export function getReviewById(data) {
  return request({
    url: '/creation/review/getReviewById',
    method: 'post',
    data
  })
}
export function getReviewCount(data) {
  return request({
    url: '/creation/review/getReviewCount',
    method: 'post',
    data
  })
}
export function getReplyByReviewId(data) {
  return request({
    url: '/creation/review/getReplyByReviewId',
    method: 'post',
    data
  })
}
export function saveReview(data) {
  return request({
    url: '/creation/review/saveReview',
    method: 'post',
    data
  })
}
export function saveReply(data) {
  return request({
    url: '/creation/review/saveReply',
    method: 'post',
    data
  })
}

export function getBySearch(data) {
  return request({
    url: '/search/search?search=' + data,
    method: 'get',
    data
  })
}
export function listClassify(data) {
  return request({
    url: '/creation/classify/listClassify',
    method: 'get',
    data
  })
}

export function getHotList() {
  return request({
    url: '/hot/hot/listHotQuestion',
    method: 'get'
  })
}

export function listHotByClassify(data) {
  return request({
    url: '/hot/hot/listHotByClassify',
    method: 'post',
    data
  })
}
