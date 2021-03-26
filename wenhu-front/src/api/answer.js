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
